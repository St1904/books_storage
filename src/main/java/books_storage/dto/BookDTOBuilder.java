package books_storage.dto;

public class BookDTOBuilder {
    
    private Long id;
    private String name;
    private Integer shelf;
    private RackDTO rack;
    
    public static BookDTOBuilder create() {
        return new BookDTOBuilder();
    }

    public BookDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public BookDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BookDTOBuilder withShelf(Integer shelf) {
        this.shelf = shelf;
        return this;
    }

    public BookDTOBuilder withRack(RackDTO rack) {
        this.rack = rack;
        return this;
    }

    public BookDTOBuilder withRack(Long id) {
        RackDTO rackDTO = null;
        if (id != null) {
            rackDTO = new RackDTO(id);
        }
        return withRack(rackDTO);
    }

    public BookDTO build() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(id);
        bookDTO.setName(name);
        bookDTO.setShelf(shelf);
        bookDTO.setRack(rack);
        return bookDTO;
    }
}
