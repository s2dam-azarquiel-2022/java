        SELECT e.codequipo,
               e.nombre,
               e.presidente,
               e.entrenador,
               e.añofundacion,
               e.estadio,
               e.sitioweb
        FROM equipo e
        WHERE e.codequipo IN (
          SELECT t.equipo
          FROM equipotemporada t
          WHERE t.temporada = '2004-2005'
        )
