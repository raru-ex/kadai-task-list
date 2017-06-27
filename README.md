# 実行手順
## 各種ファイル準備
1. プロジェクト直下の`create-local-mysql-db.sh`をlocalのmysqlへアクセスできるように調整
2. 必要に応じて`create-mysql-db.sql`を修正
3. 上記の設定に応じて`evn/dev.conf`を修正

## 初期化
1. `create-local-mysql-db.sh`を実行
2. `sbt flywayMigrate`を実行

## 動作確認
1. `sbt run`を実行
2. ブラウザから`http://localhost:9000/`を開き動作を確認