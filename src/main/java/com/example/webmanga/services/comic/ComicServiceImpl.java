package com.example.webmanga.services.comic;

import com.example.webmanga.dtos.AccountDTO;
import com.example.webmanga.dtos.ComicDTO;
import com.example.webmanga.response.ResponseObject;
import com.example.webmanga.entities.Comic;
import com.example.webmanga.repositories.ComicRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ComicServiceImpl implements ComicService {

    @Autowired
    ComicRepository comicRepository;

    @Override
    public ResponseObject addComic(ComicDTO comicDTO) {
        return new ResponseObject("Success", "Add comic successfully",
                new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }

    @Override
    public ResponseObject editComic(ComicDTO comicDTO) {
//        Comic comicEdited = comicRepository.findById(comicDTO.getId())
//                                .map(comic -> {
//                                    return comic;
//                                }).orElseGet(() -> { return null; });
//        if (comicEdited == null) {
//            return new ResponseObject("Fail", "Comic not found", "");
//        }
        return new ResponseObject("Success", "Updated successfully", new ComicDTO(comicRepository.save(new Comic(comicDTO))));
    }

    @Override
    public ResponseObject getComicInfo(String id) {
        //String pdfPath = "https://firebasestorage.googleapis.com/v0/b/testjdf-debe9.appspot.com/o/LC-purple.pdf?alt=media&token=26071fbd-5fa7-489e-9414-a45485c81313";
        String pdfPath = "D:/Image/LC-purple.pdf";
        File file = new File(pdfPath);
        System.out.println(file.toString());
        try (PDDocument doc = PDDocument.load(file)) {
            PDFRenderer pdfRenderer = new PDFRenderer(doc);
            List<byte[]> bytesList = new ArrayList<>();
            for (int page = 0; page < doc.getNumberOfPages(); ++page) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                byte[] bytes = toByteArray(bim, "png");
                bytesList.add(bytes);
            }
            return new ResponseObject("Success", "Get comic successfully", bytesList);
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseObject("Fail", "Comic not found", "");
//        Comic comic = comicRepository.findComicById(id)
//                .map(comicFound -> {
//                    return comicFound;
//                }).orElseGet(() -> { return null; });
//        if (comic == null) {
//            return new ResponseObject("Fail", "Comic not found", "");
//        }
//        return new ResponseObject("Success", "Get comic successfully", new ComicDTO(comic));
    }
    public static byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }
    @Override
    public ResponseObject searchComics(String name) {
        List<ComicDTO> comicDTOList = new ArrayList<>();
        List<Comic> comicList = comicRepository.findByName(name);
        comicList.forEach(comic -> {
            comicDTOList.add(new ComicDTO(comic));
        });
        return new ResponseObject("Success", "Found comics successfully", comicList);
    }



}
