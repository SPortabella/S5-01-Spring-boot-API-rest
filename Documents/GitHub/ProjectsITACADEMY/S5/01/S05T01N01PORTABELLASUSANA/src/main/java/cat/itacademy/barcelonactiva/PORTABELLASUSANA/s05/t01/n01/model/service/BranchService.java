package cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.service;

import cat.itacademy.barcelonactiva.PORTABELLASUSANA.s05.t01.n01.model.dto.BranchDTO;

import java.util.List;

public interface BranchService {

    List<BranchDTO> findAllBranchs();

    BranchDTO findbyId(Integer id);

    BranchDTO createBranch(BranchDTO branchDTO);

    BranchDTO updateBranchById(Integer id, BranchDTO branchDTO);


    Object deleteBranchById(Integer id);
}
