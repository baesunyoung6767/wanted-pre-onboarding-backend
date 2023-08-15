# wanted-pre-onboarding-backend
원티드 프리온보딩 백엔드 인턴십 - 선발 과제

1. 배선영<br/><br/>
2. git clone https://github.com/baesunyoung6767/wanted-pre-onboarding-backend.git<br/><br/>
3. 데이터베이스 테이블 구조 <br/><img src="https://github.com/baesunyoung6767/wanted-pre-onboarding-backend/assets/87819894/70f6c76e-6d5e-4753-ab7b-1d2730178aab"><br/><br/>
4. 구현한 API의 동작을 촬영한 데모 영상 링크<br/><br/>
5. 구현 방법 및 이유에 대한 간략한 설명 <br/> a. spring security를 사용해서 회원가입 및 로그인 구현 <br/>b. security 설정으로 로그인과 회원가입은 권한 부여없이 요청 가능하지만 게시글 GET 요청만 제외 나머지는 모두 로그인 후 전달 받은 토큰으로 권한 인증이 필요함<br/>c. jwt token 인증 과정에서 403 오류 발생 : 임시로 회원 정보에 ROLE 추가해서 SimpleGrantedAuthority(user.getUserRole().name())로 권한 부여 코드 추가 - 추가적으로 오류 발생 원인 학습 필요<br/>d. user와 post는 다대일로 조인 : user는 여러 개의 post 작성이 가능하기 때문<br/>e. 게시글 수정, 삭제는 본인이 작성한 글만 가능하도록 구현 : 로그인된 사용자 이메일과 게시글 작성자 이메일 비교<br/><br/>
6. API 명세 <br/><img src="https://github.com/baesunyoung6767/wanted-pre-onboarding-backend/assets/87819894/e23a3f76-7118-4ac4-89e0-777a04f48692">
