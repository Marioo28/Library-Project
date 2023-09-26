package com.example.libraryproject.controller;

import com.example.libraryproject.model.DTO.PublisherDTO;
import com.example.libraryproject.model.Publisher;
import com.example.libraryproject.service.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("publisherId") int id, Model model) {
        final Publisher publisher = publisherService.findPublisherById(id);
        model.addAttribute("publisher1", publisher);
        return "update-publisher";
    }
    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("publishers") Publisher thePublisher) {
        publisherService.updatePublisher(thePublisher);
        return "redirect:/api/publishers";
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
