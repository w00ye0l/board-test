# Board CRUD

## Feature
- 간단 게시판 CRUD

## Detail
- `GET` `/`
  - 게시판 전체 글 확인 페이지
- `GET` `/board`
  - 게시글 작성 폼
- `POST` `/board`
  - 게시글 작성
- `GET` `/board/{id}`
  - 게시글 상세 페이지
- `POST` `/board/{id}/edit`
  - 게시글 수정
- `GET` `/board/{id}/delete`
  - 게시글 삭제