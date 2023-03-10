package pl.edu.pg.s184934.filetransfer.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.s184934.filetransfer.file.entity.File;

@org.springframework.stereotype.Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
