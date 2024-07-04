package my.exam.catalog.apiserver.libapiserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import my.exam.catalog.apiserver.libapiserver.dto.BookDTO;
import my.exam.catalog.apiserver.libapiserver.entity.AuthorEntity;

public class Utils {

//    public <T> Optional<List<T>> getIntersection(Optional<List<T>> array1, Optional<List<T>> array2){
//        List<T> result = new ArrayList<>();
//        if(array1.isPresent()){
//            result =  array1.get();
//            if(array2.isPresent()){
//                result.retainAll(array2.get());
//                return Optional.of(result);
//            }else{
//                return array1;
//            }
//        }else{
//            return array2;
//        }
//    }

    public static Optional<List<BookDTO>> getIntersectionBooks(Optional<List<BookDTO>> books1,
            Optional<List<BookDTO>> books2) {
        List<BookDTO> result = new ArrayList<>();
        if (books1.isPresent()) {
            result = books1.get();
            if (result.isEmpty()) {
                return books2;
            } else {
                if (books2.isPresent()) {
                    List<BookDTO> tmp = books2.get();
                    if(tmp.isEmpty()){
                        return Optional.of(result);
                    }
                    result.retainAll(books2.get());
                    return Optional.of(result);
                } else {
                    return books1;
                }
            }
        } else {
            return books2;
        }
    }

    public static Optional<List<AuthorEntity>> getIntersectionAuthors(Optional<List<AuthorEntity>> authors1,
            Optional<List<AuthorEntity>> authors2) {
        List<AuthorEntity> result = new ArrayList<>();
        if (authors1.isPresent()) {
            result = authors1.get();
            if (result.isEmpty()) {
                return authors2;
            } else {
                if (authors2.isPresent()) {
                    List<AuthorEntity> tmp = authors2.get();
                    if (tmp.isEmpty()) {
                        return Optional.of(result);
                    }
                    result.retainAll(tmp);
                    return Optional.of(result);
                } else {
                    return authors1;
                }
            }
        } else {
            return authors2;
        }
    }

}
