package com.swengGUI;

import java.io.*;

public class OutputGenerator {

    public OutputGenerator() {

    }

    public void writeMakeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("template.mak"))) {
            writer.write("# Declaration of variables");
            writer.write("\nCC = g++");
            writer.write("\nCC_FLAGS = -w");
            writer.write("\n\n# File names");
            writer.write("\nEXEC = run");
            writer.write("\nSOURCES = $(wildcard *.cpp)");
            writer.write("\nOBJECTS = $(SOURCES:.cpp=.o)");
            writer.write("\n\n# Main target");
            writer.write("\n$(EXEC): $(OBJECTS)");
            writer.write("\n\t$(CC) $(OBJECTS) -o $(EXEC)");
            writer.write("\n\n# To obtain object files");
            writer.write("\n%.o: %.cpp");
            writer.write("\n\t$(CC) -c $(CC_FLAGS) $< -o $@");
            writer.write("\n\n# To remove generated files");
            writer.write("\nclean:");
            writer.write("\n\trm -f $(EXEC) $(OBJECTS));");
            writer.write("");

            writer.close();
        } catch (Exception e) {
                System.out.println("Error!");
        }
    }
}
