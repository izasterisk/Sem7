package vn.edu.fpt.jpos.services;

import java.util.List;
import java.util.stream.Collectors;
import vn.edu.fpt.jpos.repositories.StaffResponseRepository;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseDTO;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseERROR;

public class StaffResponseService {

    private static StaffResponseService instance;

    private StaffResponseService() {
    }

    public static StaffResponseService getIntsance() {
        if (instance == null) {
            instance = new StaffResponseService();
        }
        return instance;
    }

    public StaffResponseDTO add(StaffResponseDTO res) throws StaffResponseERROR {
        StaffResponseRepository repo = StaffResponseRepository.getInstance();
        return repo.add(res);
    }

    public List<StaffResponseDTO> getListByStatus(String status)
            throws StaffResponseERROR {
        StaffResponseRepository repo = StaffResponseRepository.getInstance();
        List<StaffResponseDTO> list = repo.getAll();
        return list.stream()
                .filter(res -> status.contains(res.getStatus().toString()))
                .collect(Collectors.toList());
    }

    public StaffResponseDTO getById(String id) throws StaffResponseERROR {
        StaffResponseRepository repo = StaffResponseRepository.getInstance();
        List<StaffResponseDTO> list = repo.getAll();
        return list.stream()
                .filter(res -> res.getId().equals(id))
                .collect(Collectors.toList()).get(0);
    }

    public StaffResponseDTO update(StaffResponseDTO res) throws StaffResponseERROR {
        StaffResponseRepository repo = StaffResponseRepository.getInstance();
        return repo.update(res);
    }
}
