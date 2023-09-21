package com.example.libraryproject.controller;

import com.example.libraryproject.model.Author;
import com.example.libraryproject.model.DTO.PublisherDTO;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.service.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping()
    public String showPublishers(final ModelMap modelMap) {
        List<PublisherDTO> publishers = publisherService.findAllPublishersDTO();
        modelMap.addAttribute("publishers", publishers);
        modelMap.addAttribute("publisher", new PublisherDTO());
        return "publishers";
    }

    @GetMapping("/addPublisher")
    public String showCreateForm(Publisher publisher) {
        return "add-publisher";
    }

    @RequestMapping("/add-publisher")
    public String createPublisher(Publisher publisher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-publisher";
        }
        publisherService.savePublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublishers());
        return "redirect:/api/publishers";
    }

    @GetMapping("/updatePublisher/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        final Publisher publisher = publisherService.findPublisherById(id);

        model.addAttribute("publisher", publisher);

        return "update-publisher";
    }

    @RequestMapping("/update-publisher/{id}")
    public String updatePublisher(@PathVariable("id") int id, Publisher publisher, BindingResult result, Model model) {
        if (result.hasErrors()) {
            publisher.setId(id);
            return "update-publisher";
        }

        publisherService.savePublisher(publisher);
        model.addAttribute("author", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }

    @RequestMapping("/deletePublisher/{id}")
    public String removeById(@PathVariable("id") int id, Model model) {
        publisherService.removePublisherById(id);
        List<PublisherDTO> publishers = publisherService.findAllPublishersDTO();
        model.addAttribute("publishers", publishers);
        return "publishers";
    }
//    @PostMapping("/savePublisher")
//    public Publisher savePublisher(@RequestBody Publisher publisher) {
//        return publisherService.savePublisher(publisher);
//    }
//
//    @GetMapping("/find/{id}")
//    public ResponseEntity<PublisherDTO> findPublisherById(@PathVariable int id) {
//        Publisher publisher = publisherService.findPublisherById(id);
//        PublisherDTO publisherDTO = modelMapper.map(publisher, PublisherDTO.class);
//        return ResponseEntity.ok().body(publisherDTO);
//    }
//
//    @GetMapping("/getAllPublishers")
//    public List<PublisherDTO> findAllPublishers() {
//        return publisherService.findAllPublishers().stream()
//                .map(publisher -> modelMapper.map(publisher, PublisherDTO.class))
//                .collect(Collectors.toList());
//    }


//    @Transactional
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> removeById(@PathVariable int id) {
//        publisherService.removePublisherById(id);
//        return ResponseEntity.noContent().build();
//    }

//    @GetMapping("/getPublisherBook")
//    public Map<String, Integer> getAuthorBookCount() {
//        return publisherService.getPublisherBookCount();
//    }
}
