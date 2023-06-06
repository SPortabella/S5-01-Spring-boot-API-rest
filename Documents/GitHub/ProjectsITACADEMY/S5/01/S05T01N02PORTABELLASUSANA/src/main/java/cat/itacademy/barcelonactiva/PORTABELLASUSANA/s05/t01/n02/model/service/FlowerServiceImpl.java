package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.service;

import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.exceptions.ElementNotFoundException;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n02.model.repository.FlowerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    ModelMapper modelMapper;
    private final FlowerRepository flowerRepository;

    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }


    @Override
    public List<FlowerDTO> findAllFlowers() {
        List<Flower> flowers = flowerRepository.findAll();
        //return flowers.stream().map(this::convertEntityToDto).collect(Collectors.toList());
        List<FlowerDTO> flowerDTOS = flowers.stream().map(this::convertEntityToDto).toList();
        return flowerDTOS;

    }

@Override
    public FlowerDTO findById(Integer id) {
        Optional<Flower> flowerOptional = flowerRepository.findById(id);
        if (flowerOptional.isEmpty())
            throw new ElementNotFoundException(Flower.class, id);
        else
            return convertEntityToDto(flowerOptional.get());
    }

    @Override
    public FlowerDTO createFlower(FlowerDTO flowerDTO) {
        Flower flower = flowerRepository.save(convertDtoToEntity(flowerDTO));
        return convertEntityToDto(flower);
    }

    @Override
    public Object deleteFlowerById(Integer id) {
        Optional<Flower> flowerOptional = flowerRepository.findById(id);
        if (flowerOptional.isEmpty())
            throw new ElementNotFoundException(Flower.class, id);
        else {
            flowerRepository.delete(flowerOptional.get());
            return "Flower with id " + flowerOptional.get().getId() + " deleted";
        }
    }

    private Flower convertDtoToEntity(FlowerDTO flowerDTO) {
        Flower flower = modelMapper.map(flowerDTO, Flower.class);
        return flower;
    }

    private FlowerDTO convertEntityToDto(Flower flower) {
        return new FlowerDTO(flower.getId(), flower.getName(), flower.getCountry());
    }


}
