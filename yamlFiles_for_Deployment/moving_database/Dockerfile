FROM postgres

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=ragingbull
ENV POSTGRES_DB=bank

COPY ./export_202206221620.sql /docker-entrypoint-initdb.d
