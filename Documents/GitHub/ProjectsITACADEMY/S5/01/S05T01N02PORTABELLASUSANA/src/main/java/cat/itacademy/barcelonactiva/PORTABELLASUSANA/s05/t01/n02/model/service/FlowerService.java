package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.dto.FlowerDTO;

import java.util.List;

public interface FlowerService {
    List<FlowerDTO> findAllFlowers();

    FlowerDTO findById(Integer id);

    Object deleteFlowerById(Integer id);

    FlowerDTO createFlower(FlowerDTO flowerDTO);

}
