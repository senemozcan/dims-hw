package com.example.dimshw.Controller;

import com.example.dimshw.Exceptions.BaseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class HelloController {
    @GetMapping("")
    public String showHomePage()
    {
        return "index";
    }
    @ExceptionHandler(BaseException.class)
    public ModelAndView handleError(BaseException ex){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex.getMessage());
        mav.setViewName("error");
        return mav;
    }

}