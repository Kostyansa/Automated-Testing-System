package ATS.controllers;

import ATS.DTO.Exercise;
import ATS.DTO.Program;
import ATS.DTO.TestResult;
import ATS.DTO.User;
import ATS.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
public class CheckController {
    public class TableRow{
        private String idUser;
        private String username;
        private String result;

        public TableRow(String idUser, String username, String result){
            this.idUser = idUser;
            this.username = username;
            this.result = result;
        }

        public String getIdUser() {
            return idUser;
        }

        public String getUsername() {
            return username;
        }

        public String getResult() {
            return result;
        }
    }

    @Autowired
    private ProgramService programService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestResultService testResultService;

    @Autowired
    private UTestService testService;

    @RequestMapping("/checkPrograms")
    public ModelAndView check(Long idExercise){
        ModelAndView modelAndView = new ModelAndView();
        List<Long> idPrograms = programService.getIdPrograms(idExercise);
        LinkedList<TableRow> programs = new LinkedList<>();
        for (Long idProgram : idPrograms) {
            User user = userService.getById(programService.getIdUser(idProgram));
            TestResult testResult = testResultService.getTestResult(idProgram);
            String result;
            if(testResult != null){
                testService.testProgram(idExercise, programService.getProgram(idProgram));
                result = testResult.getResult();
            }
            else{
                result = "Не сдана";
            }
            programs.push(new TableRow(
                    "Id: " + user.getIdUser().toString(),
                    "Имя: " + user.getName(),
                    "Результат: " + result
            ));
        }
        modelAndView.setViewName("/checkPrograms");
        modelAndView.addObject("idExercise", idExercise);
        modelAndView.addObject("programs", programs);
        return modelAndView;
    }

}
