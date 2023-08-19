package com.garck3h.killmenshell;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: Garck3h
 * @Date: 2023/8/18
 * @Time: 13:20
 * Life is endless, and there is no end to it.
 **/
public class Agent {
    public static void agentmain(String agentArgs, Instrumentation inst) throws NotFoundException, IOException, CannotCompileException, UnmodifiableClassException, ClassNotFoundException {
        if (agentArgs == null || agentArgs.isEmpty()) {
            System.out.println("Please provide the target class name as a parameter!");
            return;
        }
        String targetClass = agentArgs;

        Class[] classxx = inst.getAllLoadedClasses();
        for (Class<?> clazz : classxx) {
            if (clazz.getName().equals(targetClass)) {
                ClassPool classPool = ClassPool.getDefault();
                ClassClassPath classClassPath = new ClassClassPath(clazz);
                classPool.insertClassPath(classClassPath);
                CtClass ctClass = classPool.get(clazz.getName());
                inst.redefineClasses(new ClassDefinition[] { new ClassDefinition(clazz, ctClass.toBytecode()) });
                System.out.println("Successfully modified class: " + targetClass);
                return;
            }
        }
        System.out.println("Class not found: " + targetClass);
    }
}
