/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006, 2007, 2008 The Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.opensource.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.calendar.impl;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sakaiproject.calendar.api.Calendar;
import org.sakaiproject.calendar.api.CalendarEdit;
import org.sakaiproject.calendar.api.CalendarEvent;
import org.sakaiproject.calendar.api.CalendarEventEdit;
import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.entity.api.Entity;
import org.sakaiproject.util.BaseDbDoubleStorage;
import org.sakaiproject.util.DoubleStorageUser;
import org.sakaiproject.util.StorageUtils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
* <p>DbCalendarService fills out the BaseCalendarService with a database implementation.</p>
* <p>The sql scripts in src/sql/chef_calendar.sql must be run on the database.</p>
*/
public class DbCalendarService
	extends BaseCalendarService
{
	/** Our logger. */
	private static Logger M_log = LoggerFactory.getLogger(DbCalendarService.class);

	/** The name of the db table holding calendar calendars. */
	protected String m_cTableName = "CALENDAR_CALENDAR";

	/** The name of the db table holding calendar events. */
	protected String m_rTableName = "CALENDAR_EVENT";

	/** If true, we do our locks in the remote database, otherwise we do them here. */
	protected boolean m_locksInDb = true;

	protected static final String[] FIELDS = { "EVENT_START", "EVENT_END", "RANGE_START", "RANGE_END" };

	/*******************************************************************************
	* Constructors, Dependencies and their setter methods
	*******************************************************************************/

	/** Dependency: SqlService */
	protected SqlService m_sqlService = null;

	/**
	 * Dependency: SqlService.
	 * @param service The SqlService.
	 */
	public void setSqlService(SqlService service)
	{
		m_sqlService = service;
	}

	/**
	 * Configuration: set the table name for the container.
	 * @param path The table name for the container.
	 */
	public void setContainerTableName(String name)
	{
		m_cTableName = name;
	}

	/**
	 * Configuration: set the table name for the resource.
	 * @param path The table name for the resource.
	 */
	public void setResourceTableName(String name)
	{
		m_rTableName = name;
	}

	/**
	 * Configuration: set the locks-in-db
	 * @param path The storage path.
	 */
	public void setLocksInDb(String value)
	{
		m_locksInDb = Boolean.valueOf(value).booleanValue();
	}

	/** Configuration: to run the ddl on init or not. */
	protected boolean m_autoDdl = false;

	/**
	 * Configuration: to run the ddl on init or not.
	 * 
	 * @param value
	 *        the auto ddl value.
	 */
	public void setAutoDdl(String value)
	{
		m_autoDdl = Boolean.valueOf(value).booleanValue();
	}

	/*******************************************************************************
	* Init and Destroy
	*******************************************************************************/

	/**
	 * Final initialization, once all dependencies are set.
	 */
	public void init()
	{
		try
		{
			// if we are auto-creating our schema, check and create
			if (m_autoDdl)
			{
				m_sqlService.ddl(this.getClass().getClassLoader(), "sakai_calendar");
			}

			super.init();
			
			SAK11204Fix sf =  new SAK11204Fix(this);
			sf.apply(m_autoDdl);

			M_log.info("init(): tables: " + m_cTableName + " " + m_rTableName + " locks-in-db: " + m_locksInDb);
		}
		catch (Throwable t)
		{
			M_log.warn("init(): ", t);
		}
	}

	/*******************************************************************************
	* BaseCalendarService extensions
	*******************************************************************************/

	/**
	* Construct a Storage object.
	* @return The new storage object.
	*/
	protected Storage newStorage()
	{
		return new DbStorage(this);

	}	// newStorage

	/*******************************************************************************
	* Storage implementation
	*******************************************************************************/

	/**
	* Covers for the BaseDbStorage, providing Chat parameters
	* Note: base class containers are reference based, this service is still id based - converted here %%%
	*/
	protected class DbStorage
		extends BaseDbDoubleStorage
		implements Storage
	{
		/**
		* Construct.
		* @param user The StorageUser class to call back for creation of Resource and Edit objects.
		*/
		public DbStorage(DoubleStorageUser user)
		{
			// TODO: what about owner, draft?
			super(m_cTableName, "CALENDAR_ID", m_rTableName, "EVENT_ID", "CALENDAR_ID",
					"EVENT_START", /* owner, draft, pubview */null, null, null, FIELDS, m_locksInDb, "calendar", "event", user, m_sqlService);

		}	// DbStorage

		/** Calendar **/
		
		public boolean checkCalendar(String ref) { return super.getContainer(ref) != null; }

		public Calendar getCalendar(String ref) { return (Calendar) super.getContainer(ref); }

		public List getCalendars() { return super.getAllContainers(); }

		public CalendarEdit putCalendar(String ref)
			{ return (CalendarEdit) super.putContainer(ref); }

		public CalendarEdit editCalendar(String ref)
			{ return (CalendarEdit) super.editContainer(ref); }

		public void commitCalendar(CalendarEdit edit)
			{ commitContainer(edit); }

		public void cancelCalendar(CalendarEdit edit)
			{ super.cancelContainer(edit); }

		public void removeCalendar(CalendarEdit edit)
			{ super.removeContainer(edit); }

		public void commitContainer(CalendarEdit edit)
		{
			// form the SQL statement and the var w/ the XML
			Document doc = StorageUtils.createDocument();
			edit.toXml(doc, new Stack());
			String xml = StorageUtils.writeDocumentToString(doc);
			String statement = doubleStorageSql.getUpdateSqlCalendarField(m_containerTableName, m_containerTableIdField);
			Object[] fields = new Object[3];
			fields[0] = edit.getEventFields();
			fields[1] = xml;
			fields[2] = edit.getReference();
			
			if (m_locksAreInDb)
			{
				// use this connection that is stored with the lock
				Connection lock = (Connection) m_locks.get(edit.getReference());
				if (lock == null)
				{
					M_log.warn("commitContainer(): edit not in locks");
					return;
				}

				// update, commit, release the lock's connection
				m_sql.dbUpdateCommit(statement, fields, null, lock);

				// remove the lock
				m_locks.remove(edit.getReference());
			}

			else if (m_locksAreInTable)
			{
				// process the update
				m_sql.dbWrite(statement, fields);

				// remove the lock
				statement = doubleStorageSql.getDeleteLocksSql();

				// collect the fields
				Object lockFields[] = new Object[2];
				lockFields[0] = m_containerTableName;
				lockFields[1] = doubleStorageSql.getRecordId(edit.getReference());
				boolean ok = m_sql.dbWrite(statement, lockFields);
				if (!ok)
				{
					M_log.warn("commitContainer: missing lock for table: " + lockFields[0] + " key: " + lockFields[1]);
				}
			}

			else
			{
				// just process the update
				m_sql.dbWrite(statement, fields);

				// remove the lock
				m_locks.remove(edit.getReference());
			}
		}

		/** Event **/
		
		public boolean checkEvent(Calendar calendar, String messageId)
			{ return super.checkResource(calendar, messageId); }

		public CalendarEvent getEvent(Calendar calendar, String id)
			{ return (CalendarEvent) super.getResource(calendar, id); }

		public List getEvents(Calendar calendar)
			{ return super.getAllResources(calendar); }

		public List getEvents(Calendar calendar, long startDate, long endDate)
         { 
			// we dont have acurate timezone information at this point, so we will make certain that we are at the start of the GMT day
			long oneHour = 60L*60L*1000L;
			long oneDay = 24L*oneHour;
			// get to the start of the GMT day
			startDate = startDate - (startDate%oneDay);
			// get to the end of the GMT day
			endDate = endDate + (oneDay-(endDate%oneDay));  
			// this will work untill 9 Oct 246953 07:00:00
			Integer startDateHours = (int)(startDate/oneHour);
			Integer endDateHours = (int)(endDate/oneHour);
			
			if ( M_log.isDebugEnabled() ) {
				M_log.debug("Selecting Range from "+(new Date(startDate)).toGMTString()+" to "+(new Date(endDate)).toGMTString());
			}
			
            String filter = "((RANGE_START > ? and RANGE_START < ? ) " +
            		"or (  RANGE_END > ? and RANGE_END < ? ) " +
            		"or ( RANGE_START < ? and RANGE_END > ? ))";
            
			List<Object> rangeValues = new ArrayList<Object>();
			rangeValues.add(startDateHours);
			rangeValues.add(endDateHours);
			rangeValues.add(startDateHours);
			rangeValues.add(endDateHours);
			rangeValues.add(startDateHours);
			rangeValues.add(endDateHours);
			
            return super.getAllResources(calendar, null, filter, true, null, rangeValues);
         }

		public CalendarEventEdit putEvent(Calendar calendar,String id)
			{ return (CalendarEventEdit) super.putResource(calendar, id, null); }

		public CalendarEventEdit editEvent(Calendar calendar, String messageId)
			{ return (CalendarEventEdit) super.editResource(calendar, messageId); }

		public void commitEvent(Calendar calendar, CalendarEventEdit edit)
			{ commitResource(calendar, edit); }

		public void commitResource(Entity container, CalendarEventEdit edit)
			{
				// form the SQL statement and the var w/ the XML
				Document doc = StorageUtils.createDocument();
				edit.toXml(doc, new Stack());
				String xml = StorageUtils.writeDocumentToString(doc);
				String statement = doubleStorageSql.getUpdate2SqlCalendar(m_resourceTableName, m_resourceTableIdField, m_resourceTableContainerIdField,
						updateSet(m_resourceTableOtherFields));
				M_log.warn("STATEMENT: " + statement);

				Object[] flds = m_user.storageFields(edit);
				if (flds == null) flds = new Object[0];
				Object[] fields = new Object[flds.length + 4];
				System.arraycopy(flds, 0, fields, 0, flds.length);
				fields[fields.length - 4] = edit.getDisplayName();
				fields[fields.length - 3] = xml;
				fields[fields.length - 2] = container.getReference();
				fields[fields.length - 1] = edit.getId();

				if (m_locksAreInDb)
				{
					// use this connection that is stored with the lock
					Connection lock = (Connection) m_locks.get(edit.getReference());
					if (lock == null)
					{
						M_log.warn("commitResource(): edit not in locks");
						return;
					}

					// update, commit, release the lock's connection
					m_sql.dbUpdateCommit(statement, fields, null, lock);

					// remove the lock
					m_locks.remove(edit.getReference());
				}

				else if (m_locksAreInTable)
				{
					// process the update
					m_sql.dbWrite(statement, fields);

					// remove the lock
					statement = doubleStorageSql.getDeleteLocksSql();

					// collect the fields
					Object lockFields[] = new Object[2];
					lockFields[0] = m_resourceTableName;
					lockFields[1] = doubleStorageSql.getRecordId(container.getReference() + "/" + edit.getId());
					boolean ok = m_sql.dbWrite(statement, lockFields);
					if (!ok)
					{
						M_log.warn("commitResource: missing lock for table: " + lockFields[0] + " key: " + lockFields[1]);
					}
				}

				else
				{
					// just process the update
					m_sql.dbWrite(statement, fields);

					// remove the lock
					m_locks.remove(edit.getReference());
				}
			}

		public void cancelEvent(Calendar calendar, CalendarEventEdit edit)
			{ super.cancelResource(calendar, edit); }

		public void removeEvent(Calendar calendar, CalendarEventEdit edit)
			{ super.removeResource(calendar, edit); }
		
		public Entity readContainerTest(String xml) {
			return readContainer(xml);
		}
		public Entity readResourceTest(Entity container, String xml) {
			return readResource(container, xml);
		}

	}   // DbStorage
}	// DbCachedCalendarService



