package ATS.controllers;

import ATS.DTO.Program;
import ATS.Services.IProgramService;
import ATS.Services.UTestService;
import ATS.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubmitController {
    @Autowired
    private UserManager userManager;

    @Autowired
    private IProgramService programService;

    @Autowired
    private UTestService testService;

    @GetMapping("/submit")
    public ModelAndView getSubmitPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("submit");
        return modelAndView;
    }

    @PostMapping("/submit")
    public ModelAndView submit(Long idExercise, String path){
        ModelAndView modelAndView = new ModelAndView();
        Long idProgram = programService.getIdProgram(userManager.getUser().getIdUser(), idExercise);
        if(idProgram != null) {
            Program program = programService.getProgram(idProgram);
            if(program != null) {
                program.setPath(path);
                programService.updateProgram(program);
            }
            else {
                program = new Program(idProgram, path);
                programService.createProgram(program);
            }
            Program currentProgram = programService.getProgram(idProgram);
            if(currentProgram != null) {
                testService.testProgram(idExercise, currentProgram);
            }
        }
        else{
            modelAndView.addObject("errorMsg", "Вам не назначена эта задача");
        }
        return modelAndView;
    }
}
