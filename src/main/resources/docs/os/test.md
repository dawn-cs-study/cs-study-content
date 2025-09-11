# 🌅 여명 프로젝트 (Dawn Project)

> **여명(Dawn)** 은 개발자들을 위한 **Markdown 기반 CS 지식 저장소 & 위키 플랫폼**입니다.  
> 문서를 쉽게 관리하고, Lambda 이벤트 기반으로 자동 배포되며, 학습과 지식 공유를 돕습니다.

---

## ✨ 주요 특징

- 📖 **Markdown → HTML 변환**  
  GitHub 저장소에 올린 `.md` 파일을 자동 변환하여 배포

- ⚡ **서버리스 이벤트 처리**  
  `S3 → Lambda → S3 + CloudFront` 흐름으로 빠른 반영과 확장성 보장

- 🎯 **추천 글 제공**  
  글을 읽는 사용자가 더 공부할 수 있도록 관련 문서를 AI 기반 추천 (예정)

- 🧑‍💻 **실습 중심 학습**  
  Runner API 연동을 통해 코드 실행 환경 제공

---

## 🛠️ 아키텍처 흐름

flowchart LR
  A[S3 Upload] --> B[Lambda Trigger]
  B --> C[Markdown → HTML 변환]
  C --> D[S3 저장]
  D --> E[CloudFront 배포]
  E --> F[사용자 브라우저]


⸻

📂 기술 스택

영역	기술
프론트/문서	Markdown, HTML, CSS
서버리스	AWS S3, Lambda
배포/전달	CloudFront CDN
백엔드 연동	Spring Boot, Runner API
CI/CD	GitHub Actions


⸻

💻 예시 코드

✅ Spring Boot Controller (Runner API 연동)

```java
@RestController
@RequiredArgsConstructor
@RequestMapping("/runner")
public class RunnerController {

    private final CodeRunnerService runnerService;

    @PostMapping("/execute")
    public ResponseEntity<String> execute(@RequestBody String code) {
        String result = runnerService.run(code);
        return ResponseEntity.ok(result);
    }
}
```

⸻

🚀 기대 효과
	•	⏱️ 배포 속도 향상: Lambda 이벤트 기반 → 즉시 변환 & 배포
	•	💰 비용 최적화: 서버리스 구조 + CDN 캐싱
	•	📊 운영 지표 개선: TTFB 단축, 캐시 히트율 상승, 배포 횟수 감소

⸻

🌅 여명 프로젝트는 개발자의 학습과 지식 공유를 새벽빛처럼 밝히는 것을 목표로 합니다.


