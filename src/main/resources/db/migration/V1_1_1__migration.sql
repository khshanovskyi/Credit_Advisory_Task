create table if not exists advisors
(
    id               BIGSERIAL PRIMARY KEY,
    first_name       VARCHAR(25) not null,
    last_name        VARCHAR(25) not null,
    email            VARCHAR(30) not null unique,
    cognito_username VARCHAR(25) not null unique,
    role             VARCHAR(10) not null
);

create table if not exists applicants
(
    id               BIGSERIAL PRIMARY KEY,
    first_name       VARCHAR(25) not null,
    last_name        VARCHAR(25) not null,
    email            VARCHAR(30) not null unique,
    cognito_username VARCHAR(25) not null unique,
    ssn              VARCHAR(60) not null unique,
    city             VARCHAR(60) not null,
    street           VARCHAR(60) not null,
    number           VARCHAR(60) not null,
    apt              VARCHAR(60),
    zip_code         VARCHAR(60) not null
);

create table if not exists phone_applicants
(
    number       NUMERIC(10, 0) not null unique,
    phone_type   VARCHAR(60)    not null,
    applicant_id BIGINT         not null
        constraint fk_applicants_phone_applicants REFERENCES applicants (id)
);

create table if not exists applications
(
    id           BIGSERIAL PRIMARY KEY,
    amount_usd   NUMERIC(10, 2) not null,
    status       VARCHAR(25)    not null,
    applicant_id BIGINT         not null
        constraint fk_advisors_applicant REFERENCES advisors (id),
    advisor_id   BIGINT
        constraint fk_applications_applicant REFERENCES applicants (id),
    created_at   TIMESTAMP default now(),
    assigned_at  TIMESTAMP,
    resolved_at  TIMESTAMP
);

-- advisors
INSERT INTO public.advisors (id, first_name, last_name, email, cognito_username, role)
VALUES (1, 'Paolo', 'Romolo', 'paolo.romolo@bank.ua', 'paolo_romolo', 'ASSOCIATE');
INSERT INTO public.advisors (id, first_name, last_name, email, cognito_username, role)
VALUES (2, 'Nozi', 'Kibasa', 'nozi.kibasa@bank.ua', 'nozi_kibasa', 'ASSOCIATE');
INSERT INTO public.advisors (id, first_name, last_name, email, cognito_username, role)
VALUES (3, 'Lorani', 'Ovari', 'lorani.ovari@bank.ua', 'lorani_lovari', 'PARTNER');
INSERT INTO public.advisors (id, first_name, last_name, email, cognito_username, role)
VALUES (4, 'Lina', 'Cipulina', 'lina.cipulina@bank.ua', 'lina_cipulina', 'PARTNER');
INSERT INTO public.advisors (id, first_name, last_name, email, cognito_username, role)
VALUES (5, 'Bahavtri', 'Momari', 'bahavtri.momari@bank.ua', 'bahavtri_momari', 'SENIOR');

-- applicants
INSERT INTO public.applicants (id, first_name, last_name, email, cognito_username, ssn, city, street, number, apt,
                               zip_code)
VALUES (1, 'Nikola', 'Rukola', 'nikola.rokola@gmail.com', 'nicolo_rucola', '48fjs993399fkk', 'Kyiv', 'Bandery', '11',
        null, '62133');
INSERT INTO public.applicants (id, first_name, last_name, email, cognito_username, ssn, city, street, number, apt,
                               zip_code)
VALUES (2, 'Bandi', 'Paramdi', 'bandi.paramdi@gmail.com', 'bandi_paramdi', '58349fjjixvs', 'Kyiv', 'Bandery', '12', '2',
        '62133');
INSERT INTO public.applicants (id, first_name, last_name, email, cognito_username, ssn, city, street, number, apt,
                               zip_code)
VALUES (3, 'Mykyta', 'Kolola', 'mykyta.kolola@gmail.com', 'mykyta_kolola', '8549rj338fdu3u7', 'Lviv', 'Bandery', '1',
        '1', '65333');
INSERT INTO public.applicants (id, first_name, last_name, email, cognito_username, ssn, city, street, number, apt,
                               zip_code)
VALUES (4, 'Victoria', 'Nuland', 'vika.nuland@gmail.com', 'vika_nuland', 'fkdkkf85848wj', 'Kharkiv', 'Bandery', '33',
        null, '67999');
INSERT INTO public.applicants (id, first_name, last_name, email, cognito_username, ssn, city, street, number, apt,
                               zip_code)
VALUES (5, 'Liz', 'Trus', 'liz.trus@gmail.com', 'liz_trus', '4ifju3899iwje', 'Ivano-Frankivsk', 'Bandery', '11', '2',
        '55555');

-- phone_applicants
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (123456789, 'HOME', 1);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (123456799, 'MOBILE', 1);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (123456999, 'WORK', 1);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (987654321, 'HOME', 2);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (987654322, 'MOBILE', 2);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (987654323, 'WORK', 2);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (987654341, 'HOME', 3);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (987654441, 'WORK', 3);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (987644441, 'MOBILE', 3);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (555555555, 'WORK', 4);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (555555552, 'HOME', 4);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (555555551, 'MOBILE', 4);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (443333333, 'WORK', 5);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (443333332, 'MOBILE', 5);
INSERT INTO public.phone_applicants (number, phone_type, applicant_id)
VALUES (443333331, 'HOME', 5);

-- applications
INSERT INTO public.applications (id, amount_usd, status, applicant_id, advisor_id, created_at, assigned_at, resolved_at)
VALUES (3, 7500.00, 'NEW', 1, null, '2022-10-02 13:21:22.656940', null, null);
INSERT INTO public.applications (id, amount_usd, status, applicant_id, advisor_id, created_at, assigned_at, resolved_at)
VALUES (4, 25000.00, 'NEW', 2, null, '2022-10-02 13:21:53.910236', null, null);
INSERT INTO public.applications (id, amount_usd, status, applicant_id, advisor_id, created_at, assigned_at, resolved_at)
VALUES (5, 100000.00, 'NEW', 3, null, '2022-10-02 13:23:00.719094', null, null);
INSERT INTO public.applications (id, amount_usd, status, applicant_id, advisor_id, created_at, assigned_at, resolved_at)
VALUES (6, 200.00, 'NEW', 4, null, '2022-10-02 13:23:00.719094', null, null);
INSERT INTO public.applications (id, amount_usd, status, applicant_id, advisor_id, created_at, assigned_at, resolved_at)
VALUES (7, 49999.00, 'NEW', 5, null, '2022-10-02 13:23:00.719094', null, null);
