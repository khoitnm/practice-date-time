import React, {useEffect, useState} from 'react';
import './App.css';
import dateTimeBeService from "./DateTimeBeService";
import {DateTimeEntity} from "./DateTimeEntity";

function App() {
  const [dateTime, setDateTime] = useState<DateTimeEntity>();

  useEffect(() => {
    dateTimeBeService.now().then((dateTimes: Array<DateTimeEntity>) => {
      setDateTime(dateTimes[dateTimes.length - 1]);
    });
  }, []);

  const onRetrieveDateTime = async () => {
    const dateTimes: Array<DateTimeEntity> = await dateTimeBeService.now();
    setDateTime(dateTimes[dateTimes.length - 1]);
  }

  return (
    <div>
      <button onClick={onRetrieveDateTime}>Retrieve date time</button>

      <table>
        <tr>
          <td>ID</td>
          <td>{dateTime?.id}</td>
        </tr>

        <tr>
          <td>nowOffsetDateTime</td>
          <td>{dateTime?.nowOffsetDateTime}</td>
        </tr>

        <tr>
          <td>nowOffsetDateTimeInUTC</td>
          <td>{dateTime?.nowOffsetDateTimeInUTC}</td>
        </tr>

        <tr>
          <td>nowOffsetDateTimeInUTC type</td>
          <td>{typeof dateTime?.nowOffsetDateTimeInUTC}</td>
        </tr>

        <tr>
          <td>nowZonedDateTime</td>
          <td>{dateTime?.nowZonedDateTime}</td>
        </tr>

        <tr>
          <td>nowZonedDateTimeInUTC</td>
          <td>{dateTime?.nowZonedDateTimeInUTC}</td>
        </tr>
      </table>
    </div>
  );
}

export default App;
