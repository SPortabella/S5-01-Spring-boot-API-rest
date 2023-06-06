package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.controller;

import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.service.BranchServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/branch")
public class BranchController {
    BranchServiceImpl branchServiceImpl;

    public BranchController(BranchServiceImpl branchServiceImpl) {

        this.branchServiceImpl = branchServiceImpl;

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BranchDTO>> getAllBranchs() {
        List<BranchDTO> branchDTOS = branchServiceImpl.findAllBranchs();

        if (branchDTOS.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok().body(branchDTOS);
        }
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<BranchDTO> getOneBranchById(@PathVariable Integer id) {

        try {
            BranchDTO branchDTO = branchServiceImpl.findbyId(id);
            return ResponseEntity.ok().body(branchDTO);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) {
        try {
            return ResponseEntity.ok().body(branchServiceImpl.createBranch(branchDTO));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<BranchDTO> updateOneBranchById(@PathVariable Integer id,
                                                         @RequestBody BranchDTO branchDTO) {

        try {
            return ResponseEntity.ok().body(branchServiceImpl.updateBranchById(id, branchDTO));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOneBranchById(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok().body(branchServiceImpl.deleteBranchById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    //TODO Peticiones para funcionar con Thymeleaf

    @GetMapping("/v/branchs")
    public String vgetAllBranchs(Model model) {
        model.addAttribute("branchs", branchServiceImpl.findAllBranchs());
        return "branchs";

    }
    @GetMapping("/v/branchs/add")
    public String createBranchForm(Model model) {

        Branch branch = new Branch();
        model.addAttribute("branch", branch);
        return "create_branch";
    }

    @PostMapping("/v/branchs/add")
    public String saveBranch(@ModelAttribute("branch") BranchDTO branchDTO) {
        branchServiceImpl.createBranch(branchDTO);
        return "redirect:/branch/v/branchs";
    }

    @GetMapping("/v/branchs/update/{id}")
    public String updateBranchForm(@PathVariable Integer id, Model model) {
        model.addAttribute("branch", branchServiceImpl.findbyId(id));
        return "edit_branch";
    }

    @PostMapping("/v/branchs/update/{id}")
    public String updateBranch(@PathVariable Integer id,
                               @ModelAttribute("branch") BranchDTO branchDTO,
                               Model model) {
        branchServiceImpl.updateBranchById(id, branchDTO);
        return "redirect:/branch/v/branchs";
    }

    @GetMapping ("/v/branchs/delete/{id}")
    public String deleteBranch(@PathVariable Integer id) {
        branchServiceImpl.deleteBranchById(id);
        return "redirect:/branch/v/branchs";
    }

}
