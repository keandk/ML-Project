import re

def extract_and_replace_tokens(java_code):
    # Reserved keywords in Java
    java_keywords = {
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
        "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if",
        "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "null", "package",
        "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized",
        "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "Object",
        "module", "open", "opens", "permits", "provides", "record", "requires", "sealed", "to", "transitive",
        "uses", "var", "with", "yield"
    }

    # Control-flow keywords: không coi là function
    control_flow_keywords = {
        "if", "for", "while", "switch", "catch", "throw", "return", "synchronized", "instanceof", "try", "else"
    }

    variable_map = {}
    function_map = {}
    variable_counter = 1
    function_counter = 1

    # 1. Add spaces around symbols
    java_code = re.sub(r'([{}();,\.])', r' \1 ', java_code)
    java_code = re.sub(r'\s+', ' ', java_code).strip()

    # 2. Split tokens
    tokens = java_code.split()

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
                    if token not in java_keywords and token not in function_map:
                        function_map[token] = f"FUN{function_counter}"
                        function_counter += 1
                    processed_tokens.append(function_map.get(token, token))
                else:
                    processed_tokens.append(token)
            else:
                if token not in java_keywords and token not in variable_map and token not in function_map:
                    variable_map[token] = f"VAR{variable_counter}"
                    variable_counter += 1
                processed_tokens.append(variable_map.get(token, token))
        else:
            processed_tokens.append(token)

        i += 1

    return ' '.join(processed_tokens)

# Example usage
java_code = """
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
