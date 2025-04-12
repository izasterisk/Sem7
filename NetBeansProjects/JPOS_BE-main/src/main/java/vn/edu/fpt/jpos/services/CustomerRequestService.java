package vn.edu.fpt.jpos.services;

import java.util.List;
import java.util.stream.Collectors;
import vn.edu.fpt.jpos.repositories.CustomerRequestRepository;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestDTO;
import vn.edu.fpt.jpos.repositories.entities.customerrequest.CustomerRequestERROR;

public class CustomerRequestService {

    private static CustomerRequestService instance;

    private CustomerRequestService() {
    }

    public static CustomerRequestService getInstance() {
        if (instance == null) {
            instance = new CustomerRequestService();
        }
        return instance;
    }

    public CustomerRequestDTO addContact(CustomerRequestDTO contact)
            throws CustomerRequestERROR {
        CustomerRequestRepository repo = CustomerRequestRepository.getInstance();
        return repo.addNewContact(contact);
    }

    public List<CustomerRequestDTO> getAll() throws CustomerRequestERROR {
        CustomerRequestRepository repo = CustomerRequestRepository.getInstance();
        return repo.getAll();
    }

    public CustomerRequestDTO getById(String id)
            throws CustomerRequestERROR {
        CustomerRequestRepository repo = CustomerRequestRepository.getInstance();
        List<CustomerRequestDTO> list = repo.getAll();
        return list.stream()
                .filter(cr -> cr.getId().equals(id))
                .collect(Collectors.toList())
                .get(0);
    }

    public CustomerRequestDTO update(CustomerRequestDTO contact)
            throws CustomerRequestERROR {
        CustomerRequestRepository repo = CustomerRequestRepository.getInstance();
        return repo.update(contact);
    }

    public List<CustomerRequestDTO> getByStatus(String status) {
        CustomerRequestRepository repo = CustomerRequestRepository.getInstance();
        List<CustomerRequestDTO> list = repo.getAll();
        return list.stream()
                .filter(cr -> status.contains(cr.getStatus().toString()))
                .collect(Collectors.toList());
    }
}
