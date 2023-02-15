package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, "(");
        String declaration = tokenizer.nextToken();
        String arguments = tokenizer.nextToken();
        MethodSignature methodSignature = new MethodSignature(null, new ArrayList<>());
        tokenizer = new StringTokenizer(declaration, " ");
        List<String> accessModifiers = new ArrayList<>();
        accessModifiers.add("public");
        accessModifiers.add("private");
        accessModifiers.add("protected");
        String token = tokenizer.nextToken();
        if (accessModifiers.contains(token.toLowerCase())) {
            methodSignature.setAccessModifier(token);

            token = tokenizer.nextToken();
            methodSignature.setReturnType(token);

        } else {
            methodSignature.setAccessModifier(null);
            methodSignature.setReturnType(token);
        }

        token = tokenizer.nextToken();
        methodSignature.setMethodName(token);

        tokenizer = new StringTokenizer(arguments, " ");
        int argumentCount = tokenizer.countTokens() / 2;
        for (int i = 0; i != argumentCount; i++) {
            String type = tokenizer.nextToken();
            String value = tokenizer.nextToken();
            value = value.substring(0, value.length() - 1);
            methodSignature.getArguments().add(new MethodSignature.Argument(type, value));
        }
        return methodSignature;
    }


}
