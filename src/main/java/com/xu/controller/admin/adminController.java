package com.xu.controller.admin;

import com.xu.entity.Item;
import com.xu.entity.ShopInfo;
import com.xu.service.ItemService;
import com.xu.service.ShopInfoService;
import com.xu.util.DateUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.ws.rs.BadRequestException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/admin")
public class adminController {

    @Resource
    private ItemService itemService;

    @Resource
    private ShopInfoService shopInfoService;

    private String imageFilePath = "/home/ubuntu/shop/itemImages/";

    @RequestMapping("/item/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file, String CKEditorFuncNum) throws Exception{
        String fileName = file.getOriginalFilename();
        if(fileName == null){
            throw new BadRequestException("Invalid file name");
        }
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = DateUtil.getCurrentDateStr() + suffixName;
        System.out.println(imageFilePath);
        System.out.println(newFileName);
        System.out.println(CKEditorFuncNum);
        file.transferTo(new File(imageFilePath + newFileName));
        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">")
            .append("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + "/itemImages/" + newFileName + "','')")
            .append("</script>");
        return sb.toString();
    }

    /**
     * add, update item
     * @param item
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/item/save")
    public Map<String, Object> save(Item item, @RequestParam("imageFile") MultipartFile file) throws Exception {
        Map<String, Object> res = new HashMap<>();
        if(file != null && !file.isEmpty()){
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            file.transferTo(new File(imageFilePath + newFileName));
            item.setImage(newFileName);
        }
        item.setLastUpdate(System.currentTimeMillis());
        itemService.save(item);
        res.put("success", true);
        return res;
    }

    @RequestMapping(path = "/item/all")
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @RequestMapping(path = "/item/{id}", method = DELETE)
    public void deleteById(@PathVariable("id")Integer id){
        itemService.deleteById(id);
    }

    @RequestMapping(path = "/item/delete", method = POST)
    public Map<String, Object> deleteById(@RequestParam("ids")String ids){
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            itemService.deleteById(Integer.parseInt(id));
        }
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @RequestMapping(path = "/item/{id}", method = GET)
    public Item findById(@PathVariable("id")Integer id){
        return itemService.findById(id);
    }

    @RequestMapping(path = "/shop/info", method = GET)
    public ShopInfo getShopInfo(){
        List<ShopInfo> shopInfoList = shopInfoService.findAll();
        if(shopInfoList.isEmpty()){
            ShopInfo shopInfo = new ShopInfo();
            shopInfo.setName("爱艺购");
            shopInfo.setContent("经营各种食品百货");
            shopInfo.setContact("");
            return shopInfo;
        }
        return shopInfoList.get(0);
    }
    @RequestMapping(path = "/shop/info")
    public Map<String, Object> saveShopInfo(ShopInfo shopInfo){
        System.out.println(shopInfo);
        if(shopInfo.getName() != null && shopInfo.getContent() != null && shopInfo.getContact() != null){
            List<ShopInfo> shopInfoList = shopInfoService.findAll();
            if(shopInfoList.isEmpty()){
                shopInfoService.save(shopInfo);
            } else {
                Integer id = shopInfoList.get(0).getId();
                shopInfo.setId(id);
                shopInfoService.save(shopInfo);
            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        return res;
    }
}
