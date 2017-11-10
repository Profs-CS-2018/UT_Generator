import java.io.*;

public class OutputGenerator {

    public OutputGenerator() {

    }

    public void writeMakeFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("template.mak"));

            writer.write("# Declaration of variables");
            writer.write("CC = g++");
            writer.write("CC_FLAGS = -w");
            writer.write("# File names");
            writer.write("EXEC = run");
            writer.write("SOURCES = $(wildcard *.cpp)");
            writer.write("OBJECTS = $(SOURCES:.cpp=.o)");
            writer.write("# Main target");
            writer.write("$(EXEC): $(OBJECTS)");
            writer.write("$(CC) $(OBJECTS) -o $(EXEC)");
            writer.write("# To obtain object files");
            writer.write("%.o: %.cpp");
            writer.write("$(CC) -c $(CC_FLAGS) $< -o $@");
            writer.write("# To remove generated files");
            writer.write("clean:");
            writer.write("\trm -f $(EXEC) $(OBJECTS));");
            writer.write("");
        } catch (Exception e) {
                System.out.println("Error!");
        }
    }
}
