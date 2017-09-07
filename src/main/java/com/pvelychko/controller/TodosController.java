package com.pvelychko.controller;

import com.pvelychko.model.Todo;
import com.pvelychko.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TodosController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        List<Todo> list = todoService.findAll();
        model.put("todos", list.stream()
                        .filter(i -> !i.getComplete()).collect(Collectors.toList()));
        model.put("done", list.stream()
                        .filter(i -> i.getComplete()).collect(Collectors.toList()));

        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView createTask(@RequestParam("content") String content) {
        Todo todo = new Todo(content);
        todoService.save(todo);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/", true));

        return modelAndView;
    }

    @RequestMapping(value = "/complete/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView completeTask(@PathVariable("taskId") String taskId) {
        final Long id = Long.valueOf(taskId);
        final Todo todo = todoService.findById(id);
        todo.setComplete(!todo.getComplete());
        todoService.save(todo);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/", true));

        return modelAndView;
    }

    @RequestMapping(value = "/update/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView updateTask(@PathVariable("taskId") String taskId, @RequestParam("content") String content) {
        final Long id = Long.valueOf(taskId);
        final Todo todo = todoService.findById(id);
        todo.setContent(content);
        todoService.save(todo);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/", true));

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteTask(@PathVariable("taskId") String taskId) {
        final Long id = Long.valueOf(taskId);
        todoService.delete(id);
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/", true));

        return modelAndView;
    }

    @RequestMapping(value = "/deleteCompleted", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView deleteCompletedTasks() {
        List<Todo> list = todoService.findAll();
        list.stream()
                .filter(t -> t.getComplete()).forEach(c -> todoService.delete(c.getId()));

        ModelAndView modelAndView = new ModelAndView(new RedirectView("/", true));

        return modelAndView;
    }
}
