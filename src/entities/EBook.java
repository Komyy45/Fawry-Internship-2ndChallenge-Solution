package entities;

import enums.FileType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EBook extends PurchasableBook{

    private FileType fileType;

    public EBook(String ISBN, String title, LocalDate publishedOn, BigDecimal price, FileType fileType) {
        super(ISBN, title, publishedOn, price);
        this.fileType = fileType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }
}
