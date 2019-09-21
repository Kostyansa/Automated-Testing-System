package ATS.Services;

import ATS.DAO.ITestRepository;
import ATS.DTO.Program;
import ATS.DTO.TestResult;
import ATS.DTO.UTest;
import ATS.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UTestService {
    @Autowired
    private ITestRepository testRepository;

    @Autowired
    private IProgramService programService;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private UserManager userManager;

    private static boolean compile(String option, Path destDir, Path... files)
            throws IOException
    {
        System.err.println("compile...");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        try (StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null)) {
            Iterable<? extends JavaFileObject> fileObjects =
                    fm.getJavaFileObjectsFromPaths(Arrays.asList(files));

            List<String> options = new ArrayList<>();
            if (option != null) {
                options.add(option);
            }
            if (destDir != null) {
                options.add("-d");
                options.add(destDir.toString());
            }
            options.add("-cp");
            options.add(System.getProperty("test.classes", "."));
            JavaCompiler.CompilationTask task =
                    compiler.getTask(null, fm, null, options, null, fileObjects);
            if (!task.call())
                return false;
        }
        catch (Exception ex){
            return false;
        }
        return true;
    }

    private String[] parse(String path) {
        int i = path.length() - 1;
        int j = 0;
        while((i > -1) && (path.charAt(i) != '/')){
            if (path.charAt(i) == '.'){
                j = i;
            }
            i--;
        }
        if(j != 0 && i != 0) {
            return new String[]{"file:" + path.substring(0, i + 1), path.substring(i + 1, j)};
        }
        else{
            return null;
        }
    }

    public void testProgram(Long idExercise, Program program){
        String[] parsedPath = parse(program.getPath());
        if(parsedPath == null){
            testResultService.addTestResult(new TestResult(program.getIdProgram(), "Неправильно указан путь к программе"));
            return;
        }
        String path = parsedPath[0];
        String programName = parsedPath[1];
        long result = 0L;
        List<UTest> tests = testRepository.getTestsByExerciseId(idExercise);
        try {
            if (!compile(null, Paths.get(path),Paths.get(program.getPath()))){
                testResultService.addTestResult(new TestResult(program.getIdProgram(), "Compilation failed"));
            }
            else {
                URL classURL = new URL(path);
                URL[] urls = {classURL};
                URLClassLoader urlClassLoader = new URLClassLoader(urls);
                Class aClass = urlClassLoader.loadClass(programName + ".class");
                Method main = aClass.getMethod("main", String[].class);
                String[] params = null;
                for (UTest test : tests) {
                    UTestInstance testInstance = new UTestInstance(main, test.getData(), test.getExpectedResult());
                    if (testInstance.test()) {
                        result += 1;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        testResultService.addTestResult(new TestResult(program.getIdProgram(), result + "/" + tests.size()));
    }

}
