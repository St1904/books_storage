package books_storage.dto;

import books_storage.db.model.Rack;

public class RackDTO {

    private Long id;

    public RackDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // not the best way to do mapping, but it is quite a tiny project
    public Rack toEntity() {
        Rack rack = new Rack();
        rack.setId(id);
        return rack;
    }

    public static RackDTO of(Rack rack) {
        RackDTO rackDTO = new RackDTO();
        rackDTO.setId(rack.getId());
        return rackDTO;
    }
}
