package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.service;

import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.Exceptions.ElementNotFoundException;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.domain.Branch;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.repository.BranchRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository;

    @Autowired
    ModelMapper modelMapper;

    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<BranchDTO> findAllBranchs() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public BranchDTO findbyId(Integer id) {

        Optional<Branch> branchOptional = branchRepository.findById(id);
        if (branchOptional.isEmpty())
            throw new ElementNotFoundException(Branch.class, id);
        else
            return convertEntityToDto(branchOptional.get());
    }

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) {
        Branch branch = branchRepository.save(convertDtoToEntity(branchDTO));
        return convertEntityToDto(branch);
    }

    @Override
    public BranchDTO updateBranchById(Integer id, BranchDTO branchDTO) {
        Optional<Branch> branchOptional = branchRepository.findById(id);
        if (branchOptional.isEmpty())
            throw new ElementNotFoundException(Branch.class, id);
        else {
            if (branchDTO.getName() != null) branchOptional.get().setName(branchDTO.getName());
            if (branchDTO.getCountry() != null) branchOptional.get().setCountry(branchDTO.getCountry());
            Branch branch = branchRepository.save(branchOptional.get());
            return convertEntityToDto(branch);
        }
    }

    @Override
    public Object deleteBranchById(Integer id) {
        Optional<Branch> branchOptional = branchRepository.findById(id);
        if (branchOptional.isEmpty())
            throw new ElementNotFoundException(Branch.class, id);
        else {
            branchRepository.delete(branchOptional.get());
            return null;
        }

    }

    private Branch convertDtoToEntity(BranchDTO branchDTO) {
        Branch branch = modelMapper.map(branchDTO, Branch.class);
        return branch;
    }

    private BranchDTO convertEntityToDto(Branch branch) {
        return new BranchDTO(branch.getId(), branch.getName(), branch.getCountry());
    }

}
