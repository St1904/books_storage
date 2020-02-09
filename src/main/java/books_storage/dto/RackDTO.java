package books_storage.dto;

import books_storage.db.model.Rack;

public class RackDTO {

    private long id;

    public RackDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
