import re

def extract_and_replace_tokens(code):
    # Reserved keywords in C/C++/Java
    keywords = {
        # C keywords
        "auto", "break", "case", "char", "const", "continue", "default", "do", "double",
        "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register",
        "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef",
        "union", "unsigned", "void", "volatile", "while",
        
        # C++ additional keywords
        "asm", "bool", "catch", "class", "const_cast", "delete", "dynamic_cast",
        "explicit", "export", "false", "friend", "inline", "mutable", "namespace",
        "new", "operator", "private", "protected", "public", "reinterpret_cast",
        "static_cast", "template", "this", "throw", "true", "try", "typeid",
        "typename", "using", "virtual", "wchar_t",
        
        # Java keywords
        "abstract", "assert", "boolean", "byte", "extends", "final", "finally",
        "implements", "import", "instanceof", "interface", "native", "null", "package",
        "strictfp", "super", "synchronized", "throws", "transient",
        "Object", "module", "open", "opens", "permits", "provides", "record", 
        "requires", "sealed", "to", "transitive", "uses", "var", "with", "yield"
    }

    # Control-flow keywords: don't treat as functions
    control_flow_keywords = {
        "if", "for", "while", "switch", "catch", "throw", "return", 
        "synchronized", "instanceof", "try", "else"
    }

    variable_map = {}
    function_map = {}
    variable_counter = 1
    function_counter = 1

    # 1. Add spaces around symbols including square brackets
    code = re.sub(r'([{}()\[\];,\.])', r' \1 ', code)
    code = re.sub(r'\s+', ' ', code).strip()

    # 2. Split tokens
    tokens = code.split()

    # 3. Process tokens
    processed_tokens = []
    i = 0
    while i < len(tokens):
        token = tokens[i]

        # If it looks like an identifier
        if re.match(r'^[a-zA-Z_][a-zA-Z0-9_]*$', token):
            if (i + 1 < len(tokens)) and (tokens[i + 1] == '('):
                # Check if it's NOT a control-flow keyword
                if token not in control_flow_keywords:
                    if token not in keywords and token not in function_map:
                        function_map[token] = f"FUN{function_counter}"
                        function_counter += 1
                    processed_tokens.append(function_map.get(token, token))
                else:
                    processed_tokens.append(token)
            else:
                if token not in keywords and token not in variable_map and token not in function_map:
                    variable_map[token] = f"VAR{variable_counter}"
                    variable_counter += 1
                processed_tokens.append(variable_map.get(token, token))
        else:
            processed_tokens.append(token)

        i += 1

    return ' '.join(processed_tokens)

# Example usage
code = """
ChannelBuffer buf;
        try {
            if (response instanceof XContentRestResponse) {
                XContentBuilder builder = ((XContentRestResponse) response).builder();
                if (response.contentThreadSafe()) {
                    buf = builder.bytes().toChannelBuffer();
                } else {
                    buf = builder.bytes().copyBytesArray().toChannelBuffer();
                }
            } else {
                if (response.contentThreadSafe()) {
                    buf = ChannelBuffers.wrappedBuffer(response.content(), response.contentOffset(), response.contentLength());
                } else {
                    buf = ChannelBuffers.copiedBuffer(response.content(), response.contentOffset(), response.contentLength());
                }
            }
        }
"""
