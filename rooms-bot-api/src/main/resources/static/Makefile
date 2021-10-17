dev:
	docker-compose up --build
push:
	docker-compose build --compress --force-rm
	docker image tag zsu-cms-dev_cms:latest 31081991/zsu-cms
	docker push 31081991/zsu-cms
stop:
	docker-compose down --remove-orphans
db-console:
	docker exec -it db bash
cms-logs:
	docker logs cms