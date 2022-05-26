package byfayzullayev.mymed.service.news;

import byfayzullayev.mymed.repository.InNewsRepository;
import byfayzullayev.mymed.repository.NewsRepository;
import byfayzullayev.mymed.entity.news.InNewsEntity;
import byfayzullayev.mymed.entity.news.NewsEntity;
import byfayzullayev.mymed.model.receive.NewsReceiveModel;
import byfayzullayev.mymed.model.response.ApiResponse;
import byfayzullayev.mymed.service.base.BaseService;
import byfayzullayev.mymed.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService {
    private final NewsRepository newsRepository;
    private final InNewsRepository inNewsRepository;
    private final FileService fileService;


    @Autowired
    public NewsService(NewsRepository newsRepository, InNewsRepository inNewsRepository, FileService fileService) {
        this.newsRepository = newsRepository;
        this.inNewsRepository = inNewsRepository;
        this.fileService = fileService;
    }

    public ApiResponse addNews(NewsReceiveModel newsReceiveModel) {

        Optional<InNewsEntity> optionalInNewsEntity = inNewsRepository.findById(newsReceiveModel.getInNewsId());
        if (optionalInNewsEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        String imageUrl = fileService.saveFile(newsReceiveModel.getBase64(), newsReceiveModel.getContentType());
        if (imageUrl == null)
            return ERROR_IMAGE_CREATE;

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setImageUrl(imageUrl);
        newsEntity.setInNewsEntity(optionalInNewsEntity.get());
        newsEntity.setRemainedDate(newsReceiveModel.getRemainedDate());
        newsEntity.setName(newsReceiveModel.getName());
        newsEntity.setAbout(newsReceiveModel.getAbout());
        newsRepository.save(newsEntity);
        return SUCCESS_V2;


    }

    public ApiResponse getNewsList() {
        SUCCESS.setData(newsRepository.findAll());
        return SUCCESS;
    }

    public ApiResponse getNewsList(long id) {
        Optional<InNewsEntity> optionalInNewsEntity = inNewsRepository.findById(id);
        if (optionalInNewsEntity.isEmpty())
            return ERROR_CATEGORY_NOT_FOUND;

        List<NewsEntity> newsEntityList = newsRepository.findByInNewsId(id);
        SUCCESS.setData(newsEntityList);
        return SUCCESS;
    }

    public ApiResponse updateNews(long id, NewsEntity newsEntity) {
        Optional<NewsEntity> optionalNewsEntity = newsRepository.findById(id);
        if (optionalNewsEntity.isPresent()) {
            NewsEntity updateId = optionalNewsEntity.get();
            Optional<NewsEntity> updateCategory = newsRepository.findByName(newsEntity.getName());

            if (updateCategory.isPresent())
                return USER_EXIST;

            updateId.setName(newsEntity.getName());
            newsRepository.save(updateId);
        }
        return SUCCESS_V2;


    }


    public ApiResponse deleteNews(long id) {
        Optional<NewsEntity> optionalNewsEntity = newsRepository.findById(id);
        if (optionalNewsEntity.isPresent()) {
            newsRepository.deleteById(id);

            return SUCCESS_V2;
        }
        return ERROR_DELETE;
    }
}
