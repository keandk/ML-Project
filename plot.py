import matplotlib.pyplot as plt
import numpy as np
import pandas as pd

# Data from classification reports
java_data = {
    'Class 0': {'precision': 0.6285, 'recall': 0.8765, 'f1_score': 0.7321},
    'Class 1': {'precision': 0.9245, 'recall': 0.7449, 'f1_score': 0.8251}
}

cpp_data = {
    'Class 0': {'precision': 0.8410, 'recall': 0.9038, 'f1_score': 0.8713},
    'Class 1': {'precision': 0.9805, 'recall': 0.9659, 'f1_score': 0.9732}
}

# Calculate FPR and FNR
def calculate_error_rates(data):
    for class_name in data:
        data[class_name]['fpr'] = 1 - data[class_name]['precision']
        data[class_name]['fnr'] = 1 - data[class_name]['recall']
    return data

java_data = calculate_error_rates(java_data)
cpp_data = calculate_error_rates(cpp_data)

# Define colors
colors = {
    'precision': '#2E8B57',    # Sea Green
    'recall': '#4169E1',       # Royal Blue
    'f1_score': '#FF6347',     # Tomato
    'fpr': '#FF4500',          # Orange Red
    'fnr': '#8B0000'           # Dark Red
}

# Function to set common figure properties
def setup_figure():
    plt.figure(figsize=(10, 8))
    plt.rcParams.update({'font.size': 14})

# 1. Overall comparison - all metrics
setup_figure()
metrics = ['precision', 'recall', 'f1_score', 'fpr', 'fnr']
java_class0 = [java_data['Class 0'][m] for m in metrics]
java_class1 = [java_data['Class 1'][m] for m in metrics]
cpp_class0 = [cpp_data['Class 0'][m] for m in metrics]
cpp_class1 = [cpp_data['Class 1'][m] for m in metrics]

x = np.arange(len(metrics))
width = 0.2

plt.bar(x - 1.5*width, java_class0, width, label='Java Class 0', 
        color='lightcoral', alpha=0.8)
plt.bar(x - 0.5*width, java_class1, width, label='Java Class 1', 
        color='coral', alpha=0.8)
plt.bar(x + 0.5*width, cpp_class0, width, label='C/CPP Class 0', 
        color='lightblue', alpha=0.8)
plt.bar(x + 1.5*width, cpp_class1, width, label='C/CPP Class 1', 
        color='steelblue', alpha=0.8)

plt.xlabel('Metrics', fontsize=16)
plt.ylabel('Score', fontsize=16)
plt.title('Complete Performance Comparison', fontsize=18)
plt.xticks(x, ['Precision', 'Recall', 'F1-Score', 'FPR', 'FNR'], rotation=45, fontsize=14)
plt.legend(bbox_to_anchor=(1.05, 1), loc='upper left', fontsize=14)
plt.grid(True, alpha=0.3)
plt.ylim(0, 1.1)
plt.tight_layout()
plt.savefig('performance_comparison_overall.png', dpi=300, bbox_inches='tight')
plt.close()

# 2. Precision comparison
setup_figure()
datasets = ['Java', 'C/CPP']
class0_precision = [java_data['Class 0']['precision'], 
                   cpp_data['Class 0']['precision']]
class1_precision = [java_data['Class 1']['precision'], 
                   cpp_data['Class 1']['precision']]

x = np.arange(len(datasets))
width = 0.35

bars1 = plt.bar(x - width/2, class0_precision, width, label='Class 0', 
                color=colors['precision'], alpha=0.7)
bars2 = plt.bar(x + width/2, class1_precision, width, label='Class 1', 
                color=colors['precision'], alpha=1.0)

plt.xlabel('Dataset', fontsize=16)
plt.ylabel('Precision', fontsize=16)
plt.title('Precision Comparison', fontsize=18)
plt.xticks(x, datasets, fontsize=14)
plt.legend(fontsize=14)
plt.grid(True, alpha=0.3)
plt.ylim(0, 1.1)

# Add value labels
for i, (v0, v1) in enumerate(zip(class0_precision, class1_precision)):
    plt.text(i - width/2, v0 + 0.02, f'{v0:.3f}', ha='center', va='bottom', fontsize=14)
    plt.text(i + width/2, v1 + 0.02, f'{v1:.3f}', ha='center', va='bottom', fontsize=14)

plt.tight_layout()
plt.savefig('precision_comparison.png', dpi=300, bbox_inches='tight')
plt.close()

# 3. Recall comparison
setup_figure()
class0_recall = [java_data['Class 0']['recall'], cpp_data['Class 0']['recall']]
class1_recall = [java_data['Class 1']['recall'], cpp_data['Class 1']['recall']]

bars1 = plt.bar(x - width/2, class0_recall, width, label='Class 0', 
                color=colors['recall'], alpha=0.7)
bars2 = plt.bar(x + width/2, class1_recall, width, label='Class 1', 
                color=colors['recall'], alpha=1.0)

plt.xlabel('Dataset', fontsize=16)
plt.ylabel('Recall', fontsize=16)
plt.title('Recall Comparison', fontsize=18)
plt.xticks(x, datasets, fontsize=14)
plt.legend(fontsize=14)
plt.grid(True, alpha=0.3)
plt.ylim(0, 1.1)

# Add value labels
for i, (v0, v1) in enumerate(zip(class0_recall, class1_recall)):
    plt.text(i - width/2, v0 + 0.02, f'{v0:.3f}', ha='center', va='bottom', fontsize=14)
    plt.text(i + width/2, v1 + 0.02, f'{v1:.3f}', ha='center', va='bottom', fontsize=14)

plt.tight_layout()
plt.savefig('recall_comparison.png', dpi=300, bbox_inches='tight')
plt.close()

# 4. F1-Score comparison
setup_figure()
class0_f1 = [java_data['Class 0']['f1_score'], cpp_data['Class 0']['f1_score']]
class1_f1 = [java_data['Class 1']['f1_score'], cpp_data['Class 1']['f1_score']]

bars1 = plt.bar(x - width/2, class0_f1, width, label='Class 0', 
                color=colors['f1_score'], alpha=0.7)
bars2 = plt.bar(x + width/2, class1_f1, width, label='Class 1', 
                color=colors['f1_score'], alpha=1.0)

plt.xlabel('Dataset', fontsize=16)
plt.ylabel('F1-Score', fontsize=16)
plt.title('F1-Score Comparison', fontsize=18)
plt.xticks(x, datasets, fontsize=14)
plt.legend(fontsize=14)
plt.grid(True, alpha=0.3)
plt.ylim(0, 1.1)

# Add value labels
for i, (v0, v1) in enumerate(zip(class0_f1, class1_f1)):
    plt.text(i - width/2, v0 + 0.02, f'{v0:.3f}', ha='center', va='bottom', fontsize=14)
    plt.text(i + width/2, v1 + 0.02, f'{v1:.3f}', ha='center', va='bottom', fontsize=14)

plt.tight_layout()
plt.savefig('f1_score_comparison.png', dpi=300, bbox_inches='tight')
plt.close()

# 5. Error Rates (FPR and FNR) for Class 0
setup_figure()
class0_fpr = [java_data['Class 0']['fpr'], cpp_data['Class 0']['fpr']]
class0_fnr = [java_data['Class 0']['fnr'], cpp_data['Class 0']['fnr']]

x_pos = np.arange(4)
error_values = [class0_fpr[0], class0_fnr[0], class0_fpr[1], class0_fnr[1]]
error_labels = ['Java\nClass 0\nFPR', 'Java\nClass 0\nFNR', 
                'C/CPP\nClass 0\nFPR', 'C/CPP\nClass 0\nFNR']
error_colors = [colors['fpr'], colors['fnr'], colors['fpr'], colors['fnr']]

bars = plt.bar(x_pos, error_values, color=error_colors, alpha=0.8)
plt.xlabel('Error Type', fontsize=16)
plt.ylabel('Error Rate', fontsize=16)
plt.title('Class 0 Error Rates', fontsize=18)
plt.xticks(x_pos, error_labels, fontsize=14)
plt.grid(True, alpha=0.3)

# Add value labels
for i, v in enumerate(error_values):
    plt.text(i, v + 0.01, f'{v:.3f}', ha='center', va='bottom', fontsize=14)

plt.tight_layout()
plt.savefig('class0_error_rates.png', dpi=300, bbox_inches='tight')
plt.close()

# 6. Class 1 Error Rates
setup_figure()
class1_fpr = [java_data['Class 1']['fpr'], cpp_data['Class 1']['fpr']]
class1_fnr = [java_data['Class 1']['fnr'], cpp_data['Class 1']['fnr']]
error_values_c1 = [class1_fpr[0], class1_fnr[0], class1_fpr[1], class1_fnr[1]]
error_labels_c1 = ['Java\nClass 1\nFPR', 'Java\nClass 1\nFNR', 
                   'C/CPP\nClass 1\nFPR', 'C/CPP\nClass 1\nFNR']

bars = plt.bar(x_pos, error_values_c1, color=error_colors, alpha=0.8)
plt.xlabel('Error Type', fontsize=16)
plt.ylabel('Error Rate', fontsize=16)
plt.title('Class 1 Error Rates', fontsize=18)
plt.xticks(x_pos, error_labels_c1, fontsize=14)
plt.grid(True, alpha=0.3)

# Add value labels
for i, v in enumerate(error_values_c1):
    plt.text(i, v + 0.005, f'{v:.3f}', ha='center', va='bottom', fontsize=14)

plt.tight_layout()
plt.savefig('class1_error_rates.png', dpi=300, bbox_inches='tight')
plt.close()

# Create detailed summary table
print("\n" + "="*80)
print("COMPREHENSIVE PERFORMANCE COMPARISON")
print("="*80)

# Create DataFrame for better formatting
comparison_data = []
for dataset, data in [('Java', java_data), ('C/CPP', cpp_data)]:
    for class_name, metrics in data.items():
        comparison_data.append([
            dataset, 
            class_name,
            f"{metrics['precision']:.4f}",
            f"{metrics['recall']:.4f}",
            f"{metrics['f1_score']:.4f}",
            f"{metrics['fpr']:.4f}",
            f"{metrics['fnr']:.4f}"
        ])

df = pd.DataFrame(comparison_data, 
                 columns=['Dataset', 'Class', 'Precision', 'Recall', 
                         'F1-Score', 'FPR', 'FNR'])
print(df.to_string(index=False))

print("\n" + "="*80)
print("KEY INSIGHTS:")
print("="*80)
print("1. C/CPP dataset significantly outperforms Java across all metrics")
print("2. Java Class 0 has high FPR (37.15%) - many false alarms")
print("3. Java Class 1 has high FNR (25.51%) - missing vulnerabilities")
print("4. C/CPP shows balanced performance with low error rates")
print("5. C/CPP Class 1 achieves near-perfect performance (F1: 0.9732)")
print("="*80)

# Performance improvement calculation
print("\nPERFORMANCE IMPROVEMENTS (C/CPP vs Java):")
print("-"*50)
for class_name in ['Class 0', 'Class 1']:
    print(f"\n{class_name}:")
    for metric in ['precision', 'recall', 'f1_score']:
        java_val = java_data[class_name][metric]
        cpp_val = cpp_data[class_name][metric]
        improvement = ((cpp_val - java_val) / java_val) * 100
        print(f"  {metric.capitalize()}: {improvement:+.1f}%")