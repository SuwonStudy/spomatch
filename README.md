# 스포매치 REST API 구상

## User

| Action        | Method       | URL   | Query Param | 특이 사항 |
| ------------- |:-------------| :---- | -----------:| -------: |
| 로그인(회원가입) | POST | `/login` | - |
| 신상정보 변경 | PUT | `/users/{id}/personal` | - |
| 회원 탈퇴 | DELETE | `/users/{id}` | - |
| 회원 개별 조회 | GET | `/users/{id}` | - |
| 회원 전체 조회 | GET | `/users` | sort,page,size |

## Players

| Action        | Method       | URL   | Query Param | 특이 사항 |
| ------------- |:-------------| :---- | -----------:| -------: |
| 플레이어 생성 | POST | `/players` | - | - |
| 플레이어 프로필 사진 변경 | PUT | `/players/{id}/profile_pic` | - | - |
| 플레이어 프로필 사진 내리기 | DELETE | `/players/{id}/profile_pic` | - | - |
| 플레이어 제거 | DELETE | `/players/{id}` | - | - |
| 플레이어 개별 조회 | GET | `/players/{id}` | - | - |
| 플레이어 전체 조회 | GET | `/players` | sort,page,size | - |

## Teams

| Action        | Method       | URL   | Query Param | 특이 사항 |
| ------------- |:-------------| :---- | -----------:| -------: |
| 팀 생성 | POST | `/teams` | - | - |
| 팀 가입 신청 | POST | `/teams/{teamId}/players/{playerId}` | - | 리더가 수행 시 **수락** |
| 팀 가입 신청 취소 | PUT | `/teams/{teamId}/players/{playerId}` | - | 리더가 수행 시 **거절** |
| 팀 탈퇴 | DELETE | `/teams/{teamId}/players/{playerId}` | - | 리더가 수행 시 **강퇴** |
| 팀 해체 | DELETE | `/teams/{id}` | - | - |
| 팀 개별 조회 | GET | `/teams/{id}` | - | - |
| 팀 전체 조회 | GET | `/teams` | sort,page,size | - |

## Matches

| Action        | Method       | URL   | Query Param | 특이 사항 |
| ------------- |:-------------| :---- | -----------:| -------: |
| 매치 생성 | POST | `/matches` | - | - |
| 매치 가입 신청 | POST | `/matches/{matchId}/teams/{teamId}` | - | 홈팀 리더가 수행 시 **수락** |
| 매치 가입 신청 취소 | PUT | `/matches/{matchId}/teams/{teamId}` | - | 홈팀 리더가 수행 시 **거절** |
| 매치 취소 | DELETE | `/matches/{id}` | - | - |
| 매치 개별 조회 | GET | `/matches/{id}` | - | - |
| 매치 전체 조회 | GET | `/matches` | sort,page,size | - |
