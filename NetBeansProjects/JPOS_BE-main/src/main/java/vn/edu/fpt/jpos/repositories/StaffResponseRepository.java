package vn.edu.fpt.jpos.repositories;

import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseDAO;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseDTO;
import vn.edu.fpt.jpos.repositories.entities.staffresponse.StaffResponseERROR;

public class StaffResponseRepository {

    private static StaffResponseRepository instance;

    private StaffResponseRepository() {
    }

    public static StaffResponseRepository getInstance() {
        if (instance == null) {
            instance = new StaffResponseRepository();
        }
        return instance;
    }

    public StaffResponseDTO add(StaffResponseDTO res) throws StaffResponseERROR {
        StaffResponseDAO dao = StaffResponseDAO.getInstance();
        return dao.post(res);
    }

    public List<StaffResponseDTO> getAll() throws StaffResponseERROR {
        StaffResponseDAO dao = StaffResponseDAO.getInstance();
        return dao.gets();
    }

    public StaffResponseDTO update(StaffResponseDTO res) throws StaffResponseERROR {
        StaffResponseDAO dao = StaffResponseDAO.getInstance();
        return dao.put(res);
    }
}
