#  유튜브 검색 어플리케이션 Project : 9조
<br>

 **프로젝트 소개**

```

인기 동영상 탐색 및 검색, 저장 가능한 어플리케이션 입니다.

- 개발 기간 : 24.05.13 ~ 24.05.23 (2주일)

```

</br>

 **주요 기능**

```

1. 인기 동영상 

- 현재 유튜브에서 가장 인기있는 동영상들을 한눈에 볼 수 있습니다.

2. 카테고리 검색

- 특정 카테고리의 인기있는 동영상들을 볼 수 있습니다. 

3. 단어 검색

- 검색어를 통해 원하는 동영상들을 볼 수 있습니다.

4. 키즈 검색

- 검색 시 키즈 안전검색을 선택할 수 있습니다.

5. 좋아요 기능

- 디테일화면, 검색화면에서 좋아요를 통해 라이브러리에 저장이 가능합니다.

```

</br>


## 사용된 기술 스택
이 프로젝트에서는 다음과 같은 주요 기술들을 사용하였습니다:
- **Room**: 데이터베이스 관리를 위한 Android 라이브러리.
- **Retrofit**: 네트워크 통신을 위한 타입 안전 HTTP 클라이언트.
- **MVVM (Model-View-ViewModel)**: 아키텍처 패턴으로, UI 로직과 비즈니스 로직을 분리.
- **RecyclerView**: 리스트 형식의 데이터를 효율적으로 표시하기 위한 위젯.
- **Moshi**: JSON 직렬화 및 역직렬화를 위한 라이브러리.
- **Navigation Component**: 애플리케이션 내의 화면 간 내비게이션을 단순화하기 위한 라이브러리.

---
## 아키텍쳐 설계
![image](https://github.com/NBC-YouTube/Youtube/assets/72172581/d3a74647-d857-4874-96f1-0663a287e914)


---

#  ***App Features in Action***

<br>

### 1) Home 화면 

<br>


|최신인기 비디오|카테고리 목록|카테고리 검색|
|---|---|---|
|![메인_인기비디오](https://github.com/NBC-YouTube/Youtube/assets/72172581/edaf8dc6-f414-4048-975e-0ec37771a2b9)|![메인_카테고리](https://github.com/NBC-YouTube/Youtube/assets/72172581/b5084869-48db-47ac-9fd1-421045b5c65e)|![메인_카테고리_비디오](https://github.com/NBC-YouTube/Youtube/assets/72172581/518790c0-cc17-419e-90f5-a47150323cd0)|

- API를 통한 인기 비디오, 카테고리 출력
- RecyclerView + Spinner를 통한 리스트 출력
<br>

---
<br>

### 2) Search 화면 

<br>

|검색 기능|키즈 필터|
|---|---|
|![검색_메인](https://github.com/NBC-YouTube/Youtube/assets/72172581/0a5bd7ca-a7cb-468b-a556-ed45dbb99a7c)|![검색_키즈](https://github.com/NBC-YouTube/Youtube/assets/72172581/0f397f94-dd81-40dd-b54e-20df2bc4e744)|

|좋아요 추가|좋아요 삭제|
|---|---|
|![검색__좋아요](https://github.com/NBC-YouTube/Youtube/assets/72172581/86de1a24-a472-47be-a498-7d0bde92fff5)|![검색_좋아요_취소](https://github.com/NBC-YouTube/Youtube/assets/72172581/1285b116-00ba-4984-8f97-660d9ffcb50f)|

- 검색어를 파라미터로 API 요청
- 아이템의 좋아요 클릭으로 빠른 저장 지원
- 키즈 안전검색 지원
<br>

---
<br>

### 3) Detail 화면

<br>

|메인 아이템 선택|검색 아이템 선택|디테일 아이템 삭제|
|---|---|---|
|![메인_디테일_저장](https://github.com/NBC-YouTube/Youtube/assets/72172581/62718b58-1b53-49a6-ab83-a49f354b703f)|![검색_디테일](https://github.com/NBC-YouTube/Youtube/assets/72172581/fa4596ca-3e45-44d3-b002-387b4606ca17)|![라이브러리_저장삭제](https://github.com/NBC-YouTube/Youtube/assets/72172581/9ed28fa1-297a-417e-8414-61c18aa90c9c)|

- 메인, 검색 모두 아이템 선택시 Detail 화면으로 이동
- 좋아요 선택시 **Room**을 통한 Library에 저장
- Library 에서 저장된 아이템 확인 & 삭제 가능




















