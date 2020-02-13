package books_storage.controller;

import books_storage.dto.RackDTO;
import books_storage.service.RackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rack")
public class RackRestController {

    private final RackService rackService;

    @Autowired
    public RackRestController(RackService rackService) {
        this.rackService = rackService;
    }

    // need this for manual testing (cannot save book without rack)
    @RequestMapping(method = RequestMethod.POST)
    public RackDTO addRack(RackDTO rackDTO) {
        return rackService.save(rackDTO);
    }
}
