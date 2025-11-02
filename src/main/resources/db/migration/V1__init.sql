CREATE TABLE public.app_user
(
    id UUID NOT NULL PRIMARY KEY
);

ALTER TABLE public.app_user
    OWNER TO "partners-app-db-user";

CREATE TABLE public.meeting_plan
(
    id            UUID         NOT NULL PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    creator_id    UUID         NOT NULL,
    partner_id    UUID         NOT NULL,
    starting_date TIMESTAMP(6) NOT NULL,
    period        SMALLINT     NOT NULL,
    FOREIGN KEY (creator_id) REFERENCES app_user (id),
    FOREIGN KEY (partner_id) REFERENCES app_user (id)
);

ALTER TABLE public.meeting_plan
    OWNER TO "partners-app-db-user";



CREATE TABLE public.meeting_item
(
    id              UUID         NOT NULL PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    item_type       VARCHAR(255),
    item_order      SMALLINT     NOT NULL,
    meeting_plan_id UUID         NOT NULL,
    FOREIGN KEY (meeting_plan_id) REFERENCES meeting_plan (id)
);

ALTER TABLE public.meeting_item
    OWNER TO "partners-app-db-user";

CREATE TABLE public.meeting_instance
(
    id              UUID         NOT NULL PRIMARY KEY,
    date            TIMESTAMP(0) NOT NULL,
    meeting_plan_id UUID         NOT NULL,
    FOREIGN KEY (meeting_plan_id) REFERENCES meeting_plan (id)
);

ALTER TABLE public.meeting_instance
    OWNER TO "partners-app-db-user";




