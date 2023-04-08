# BookSearch

## Requirements
1. 책 검색 메인화면
- [x] 검색어 입력 -> 검색 버튼 클릭 -> 결과 리스트 출력
- [ ] 검색 결과 2페이지 이상 -> 페이징 처리
- [x] 책 이미지 + 제목 + 저자 + 출판사 + 가격 정보
2. 웹브라우저 화면
- [x] 결과 책 아이템 클릭 -> 브라우저 로드
3. 최근 검색 이력 화면
- 검색 -> 해당 검색어 저장
- 최근 검색어 메뉴 클릭 -> 최근 검색어 출력
- 최대 10개
4. 최근 검색 이력으로 검색한 화면
- 최근 검색 이력 아이템 클릭 -> 메인 화면 백
- 해당 검색어 결과 리스트 출력

## Tech Stack
- Room
- Paging Library
- Navigation
- Glide
- Retrofit/Gson
- Kotlin Flow/Coroutines
- ViewModel
- Hilt

## References
- [Naver Book Search API](https://developers.naver.com/docs/serviceapi/search/book/book.md)
