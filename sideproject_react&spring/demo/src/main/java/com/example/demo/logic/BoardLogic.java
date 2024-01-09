package com.example.demo.logic;

import com.example.demo.dao.BoardDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service // @Component : 빈을 등록해서 주입받아야 할 클래스
public class BoardLogic {
    Logger logger = LoggerFactory.getLogger(BoardLogic.class);
    @Autowired
    BoardDao boardDao = null;

    public int commentDelete(int bc_no) {
        logger.info("commentDelete");
        int result = 0;
        result = boardDao.commentDelete(bc_no); // #{value}, String, double
        return result;
    }

    public int commentInsert(Map<String, Object> pMap) {
        logger.info("commentInsert");
        int result = 0;
        result = boardDao.commentInsert(pMap);
        return result;
    } // end of commentInsert

    // 조건검색 - select
    public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
        logger.info("boardList");
        List<Map<String, Object>> list = null;
        list = boardDao.boardList(pMap);
        // <boardDetail을 따로 분리해야하는 이유>
        // 4) 조회수를 수정하는 로직을 이 안에서 분리할 수 없다
        return list;
    }
    // 화면과 로직은 분리되어야 한다 : MVC
    // 여기가 로직 부분에 해당. 다중처리 필요하다 : select 2번, update 1번 (총 3번. 따라서 메소드도 3개)
    // @Transactional - dirty read 방지 : 트랜잭션이 commit 되어 확정된 데이터만을 읽도록 허용
    // Trasaction이 commit되어 확정된 데이터만 읽어줌(READ_COMMITTED)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Map<String, Object>> boardDetail(Map<String, Object> pMap) {
        logger.info("boardDetail");
        // 상세보기 가져오기
        List<Map<String, Object>> list = new ArrayList<>();
        list = boardDao.boardList(pMap); // [{}]
        // 상세보기에서 size가 1이면 update 테이블 set b_hit = b_hit + 1 where b_no=2
        if(list.size()==1) {
            // 만일 요구사항이 - update를 하다가 SQLException(부적합한 식별자) 발동하면 에러페이지를 보여달라면
            // HOW?
            try {
                boardDao.hitCount(pMap);
            } catch (Exception e) {
                logger.info(e.toString());
                //e.printStackTrace();
            }
        }

        // 댓글 가져오기
        /*
            SELECT
                        bc_no, bc_writer, bc_comment, bc_date
            FROM board230527_comment a, board230527 b
            WHERE a.b_no = b.b_no
                AND a.b_no = 2; 3건
            [
                {},//bList.get(0) -상세보기내용을 담은 Map입니다.
                {
                    comments:[

              ]

           }
         ]
        */
        List<Map<String, Object>> commentList = boardDao.commentList(pMap);
        logger.info(commentList.toString()); // [{},{},{}] : b_no=2
        if (commentList!=null && commentList.size()>0) { // 댓글이 있다면
            Map<String, Object> cMap = new HashMap<>();
            cMap.put("comments", commentList);
            // 댓글 결과 3건을 36번에 선언된 list 안에 담기
            list.add(1, cMap); // 포인트!
        }
        //return null; // NullPointerException
        return list;
    }

    public int boardInsert(Map<String, Object> pMap) {
        logger.info("boardInsert : "+pMap);
        int result = 0;
        result = boardDao.boardInsert(pMap);
        return result;
    }

    public int boardDelete(int b_no) {
        logger.info("boardDelete");
        int result = 0;
        result = boardDao.boardDelete(b_no);
        return result;
    }

    public int boardUpdate(Map<String, Object> pMap) {
        logger.info("boardUpdate");
        int result = 0;
        result = boardDao.boardUpdate(pMap);
        return result;
    }

    public String imageUpload(MultipartFile image, String savePath) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        logger.info("image:" + image);
        // String savePath =
        // "C:\\JANG\\CODE\\Coding\\Workspace\\Final-Project\\Spring-GGYM\\src\\main\\webapp\\file";
        String filename = null;
        String fullPath = null;

        // 첨부파일이 존재하나?
        if (image != null && !image.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar time = Calendar.getInstance();
            filename = sdf.format(time.getTime()) + '-' + image.getOriginalFilename().replaceAll(" ", "-");
            fullPath = savePath + "\\" + filename;
            try {
                logger.info("fullPath : " + fullPath);
                File file = new File(fullPath);// 파일명만 존재하고 내용은 없는
                byte[] bytes = image.getBytes();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                bos.write(bytes);
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filename;
    }

    public byte[] imageDownload(String imageName, String savePath) {
        String fname = null;
        try {
            fname = URLDecoder.decode(imageName, "UTF-8");
            logger.info(fname);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        File file = new File(savePath, fname.trim());

        // 해당 파일을 읽어오는 객체 생성해 줌. - 이 때 파일명을 객체화 한 주소번지가 필요함.
        FileInputStream fis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try {
            while ((readCount = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, readCount);
            }
            fileArray = baos.toByteArray();
            fis.close();
            baos.close();
        } catch (IOException e) {
            throw new RuntimeException("File Error");
        }
        return fileArray;
    }////end of imageDownload
}
