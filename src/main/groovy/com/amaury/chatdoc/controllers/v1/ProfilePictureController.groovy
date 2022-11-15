package com.amaury.chatdoc.controllers.v1

import com.amaury.chatdoc.controllers.model.EmptyResponse
import com.amaury.chatdoc.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletResponse

@Controller
class ProfilePictureController {
    @Value('${picturesPath}')
    String picturesPath
    final String[] validExtensions = new String[]{"JPG", "JPEG"};
    final String pictureExtension = "jpg"
    @Autowired
    UserService userService

    @RequestMapping(value = "/upload-profile-picture", method = RequestMethod.POST)
    @ResponseBody
    ResponseEntity<EmptyResponse> uploadProfilePicture(@RequestParam("profile-picture") MultipartFile multipartFile) throws IOException {
        String userName = userService.getCurrentUser()
        String extension = extractExt(multipartFile.getOriginalFilename())
        String fileName = "${userName}.${pictureExtension}"

        if(Arrays.stream(validExtensions)
                .noneMatch(validExt -> validExt == extension.toUpperCase()))
            return new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "ERROR", message: "Invalid Extension .${extension}, Only JPG allowed"), HttpStatus.BAD_REQUEST)

        File picture = new File(getFilePath(fileName))
        while (picture.exists()) {
            picture.delete()
        }

        multipartFile.transferTo(picture);

        new ResponseEntity<EmptyResponse>(new EmptyResponse(type: "OK", message: "/download-profile-picture/"+fileName), HttpStatus.OK)
    }

    private String getFilePath(String fileName) {
        picturesPath+fileName
    }

    @RequestMapping(value = "download-profile-picture/{userName}", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    void downloadProfilePicture(HttpServletResponse response, @PathVariable("userName") String fileName) throws IOException {

        File picture = new File("${picturesPath}${fileName}.${pictureExtension}");
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        try(FileInputStream inputStream = new FileInputStream(picture)){
            StreamUtils.copy(inputStream, response.getOutputStream());
        }
    }


    private String extractExt(String fileName) {
        fileName.substring(fileName.lastIndexOf('.')+1);
    }

}
