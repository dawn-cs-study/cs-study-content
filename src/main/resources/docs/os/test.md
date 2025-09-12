# Dawn CS-Study (Lambda 기반 위키/검색 서비스)

## 📌 프로젝트 개요
**Dawn CS-Study**는 개발 지식을 마크다운으로 정리하고, 이를 **AWS Lambda + S3 + CloudFront** 기반으로 배포하는 **클라우드 네이티브 CS 지식 저장소/위키 플랫폼**입니다.  
추가적으로 **Spring AI + PGVector** 를 활용하여, 업로드된 문서를 임베딩하고 **RAG 기반 검색 및 질의응답** 기능을 제공합니다.

---

## ✨ 주요 기능
- **문서 관리**
    - GitHub → S3 업로드 시 자동 이벤트 트리거
    - Markdown → HTML 변환 후 CloudFront를 통해 CDN 배포
    - JSON → Slug Repository 업데이트

- **AI 검색/추천**
    - Markdown 텍스트를 **Vector DB(PGVector)** 에 임베딩 저장
    - RAG 기반 질문 응답 및 연관 글 추천 기능 제공

- **자동 배포 파이프라인**
    - GitHub Actions → S3 → Lambda → CloudFront 무중단 배포
    - ETag 기반 S3 동기화 및 캐시 무효화 자동 처리

---


## ⚙️ 기술 스택
- **Backend**: Java 21, Spring Boot 3, Spring Cloud Function (AWS Lambda)
- **AI**: Spring AI, pgvector (PostgreSQL 확장)
- **Infra**: AWS Lambda, S3, CloudFront, Parameter Store, CodeDeploy, GitHub Actions
- **Database**: PostgreSQL 16 (pgvector)

---

## 🚀 기대 효과
- **개발자**: 마크다운으로 CS 지식을 빠르게 정리 & 자동 배포
- **사용자**: 최신 문서를 빠르게 조회 & AI 기반 검색/추천으로 심화 학습
- **운영 측면**: 무중단 배포, 캐시 히트율 최적화, S3 이벤트 기반 자동 동기화로 운영 비용 절감