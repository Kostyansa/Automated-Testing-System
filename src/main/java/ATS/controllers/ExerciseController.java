package ATS.controllers;

import ATS.DTO.Exercise;
import ATS.Services.ExerciseService;
import ATS.Services.ProgramService;
import ATS.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExerciseController {
    @Autowired
    private ProgramService programService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/newExercise")
    public ModelAndView getNewExercisePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newExercise");
        return modelAndView;
    }

    @PostMapping("/newExercise")
    public ModelAndView newExercise(Long idExercise, String text){
        ModelAndView modelAndView = new ModelAndView();
        Exercise exercise = new Exercise(text, idExercise);
        if(exerciseService.checkUniqueExercise(exercise)){
            exerciseService.addItem(exercise);
        }
        else{
            modelAndView.addObject("errorMsg", "Задание с таким номером уже существует");
        }
        return modelAndView;
    }

    @GetMapping("/setExercise")
    public ModelAndView getSetExercisePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setExercise");
        return modelAndView;
    }

    @PostMapping("/setExercise")
    public ModelAndView setExercise(Long idUser, Long idExercise){
        ModelAndView modelAndView = new ModelAndView();
        if(userService.getById(idUser) == null){
            modelAndView.addObject("errorMsg", "Такого пользователя не существует");
        }else if(exerciseService.getExercise(idExercise) == null){
            modelAndView.addObject("errorMsg", "Такого задания не существует");
        }else{
            programService.createConnection(idUser, idExercise);
        }
        return modelAndView;
    }
}
