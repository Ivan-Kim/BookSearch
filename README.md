# BookSearch

https://user-images.githubusercontent.com/57326300/231125491-be4a941f-5178-4059-9f0a-64afdda17445.mp4

## Requirements
1. 책 검색 메인화면
- [x] 검색어 입력 -> 검색 버튼 클릭 -> 결과 리스트 출력
- [x] 검색 결과 2페이지 이상 -> 페이징 처리
- [x] 책 이미지 + 제목 + 저자 + 출판사 + 가격 정보
2. 웹브라우저 화면
- [x] 결과 책 아이템 클릭 -> 브라우저 로드
3. 최근 검색 이력 화면
- [x] 검색 -> 해당 검색어 저장
- [x] 최근 검색어 메뉴 클릭 -> 최근 검색어 출력
- [x] 최대 10개
4. 최근 검색 이력으로 검색한 화면
- [x] 최근 검색 이력 아이템 클릭 -> 메인 화면 백
- [x] 해당 검색어 결과 리스트 출력

## Tech Stack
- Room: 최근 검색어 저장
- Paging Library: 검색 결과 리스트 출력
- Navigation + Safe Args: 최근 검색어 화면 <-> 검색 메인화면
- Glide: 책 이미지 로딩
- Retrofit/Gson: 네이버 책 API 호출
- Kotlin Flow/Coroutines: 네트워크/데이터베이스 비동기적 접근
- ViewModel: MVVM 구조
- Hilt: 의존성 주입

## References
- [Naver Book Search API](https://developers.naver.com/docs/serviceapi/search/book/book.md)
- [Android Paging Advanced Codelab](https://developer.android.com/codelabs/android-paging)
- [Android Room Documentation](https://developer.android.com/training/data-storage/room)
