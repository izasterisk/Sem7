package vn.edu.fpt.jpos.repositories;

import java.util.List;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestDAO;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestDTO;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestERROR;

public class CustomerRequestRepository {

    private static CustomerRequestRepository instance;

    private CustomerRequestRepository() {
    }

    public static CustomerRequestRepository getInstance() {
        if (instance == null) {
            instance = new CustomerRequestRepository();
        }
        return instance;
    }

    public CustomerRequestDTO addNewContact(CustomerRequestDTO contact)
            throws CustomerRequestERROR {
        CustomerRequestDAO dao = CustomerRequestDAO.getInstance();
        return dao.post(contact);
    }

    public List<CustomerRequestDTO> getAll() throws CustomerRequestERROR {
        CustomerRequestDAO dao = CustomerRequestDAO.getInstance();
        return dao.gets();
    }

    public CustomerRequestDTO update(CustomerRequestDTO contact)
            throws CustomerRequestERROR {
        CustomerRequestDAO dao = CustomerRequestDAO.getInstance();
        return dao.put(contact);
    }
}
