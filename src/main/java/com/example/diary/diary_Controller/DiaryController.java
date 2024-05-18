package com.example.diary.diary_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com.example.diary.diary_Sql.DiaryService;
import com.example.diary.diary_Models.DiaryDataModel;
import com.example.diary.diary_Models.TagType;


//  コントローラークラス
@Controller
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    //  indexページ
    @GetMapping("/")
    public String diary(Model model) {
        //  データベースから取り出したデータを送る
        model.addAttribute("samples", diaryService.exeSelect30days());
        return "/index";
    }
    //  htmlのselectタグにenum情報を送る
    @ModelAttribute("tagTypes")
    public TagType[] getTagTypes() {
        return TagType.values();
    }
    //  インサート文を送るページ
    @PostMapping("/insert")
    public String submitForm(@ModelAttribute DiaryDataModel ddm, Model model) {
        //  インサートを行う
        ddm = diaryService.exeInsert(ddm);
        return "redirect:/";
    }
    //  編集ページに飛ぶ
    @GetMapping("/edit/{Id}")
    public String edit(@PathVariable("Id") Long id, Model model) {
        //  選択されたIdのみを取り出してそれを編集ページに送る
        DiaryDataModel ddm = diaryService.exeSelectOfOne(id);
        model.addAttribute("sample", ddm);
        return "/edit";
    }
    //  編集を行うupdateページ
    @PostMapping("/update")
    public String postMethodName(@ModelAttribute DiaryDataModel ddm, Model model) {
        //  updateに使う情報を送る
        diaryService.exeUpdate(ddm);
        return "redirect:/";
    }
    //  削除を行うdeleteページ
    //  javascriptから値を受け取る(json形式)
    @ResponseBody
    @PostMapping("/delete")
    public ResponseEntity<String> postMethodName(@RequestBody String id) {
        Long parseLong = Long.parseLong(id);
        diaryService.exeDelete(parseLong);
        return ResponseEntity.ok().body("deleteは正常に終了しました");
    }
}
