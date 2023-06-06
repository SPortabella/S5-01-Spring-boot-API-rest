package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.controller;

import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.service.FlowerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    FlowerServiceImpl flowerService;

    public FlowerController(FlowerServiceImpl flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FlowerDTO>> getAllFlowers() {
        List<FlowerDTO> flowerDTOS = flowerService.findAllFlowers();

        if (flowerDTOS.size() == 0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok().body(flowerDTOS);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlowerDTO> getOneFlowerById(@PathVariable Integer id) {

        try {
            FlowerDTO flowerDTO = flowerService.findById(id);
            return ResponseEntity.ok().body(flowerDTO);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<FlowerDTO> createBranch(@RequestBody FlowerDTO flowerDTO) {
        try {
            return ResponseEntity.ok().body(flowerService.createFlower(flowerDTO));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOneFlowerById(@PathVariable Integer id) {

        try {
            return ResponseEntity.ok().body(flowerService.deleteFlowerById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
