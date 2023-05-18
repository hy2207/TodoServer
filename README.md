# TodoServer
simple was for todo list using springboot

* 작업환경

* annotation --> 추가중
  - @RestController //해당 클래스가 rest api 처리하는 controller
  - @RequestMappling //RequestMapping URI 지정 (모든 method mapping, (예전방식) 특정 method 지정하고 싶으면 methhod arg추가)
  - @NoArgsConstructor 
  - @AllArgsConstructor
  - @GetMappling
  - @PathVariable //url변수 
  - @RequestParam //Get에서 이용 query string (e.g. using query parameter --> ?key=value&key2=value2)
  - @RequestBody //Post에서 이용
  - @JsonProperty //json naming
  - @ResponseBody 

* Object Mapper
  - object <-> text
  - (object -> text) get method 활용
  - (text -> object) default 생성자 필요    
