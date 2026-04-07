    console.log("app.js loaded!");
fetch('/api/muscles')
  .then(response => response.json())
  .then(muscles => {

    const list = document.getElementById('muscle-list');
    list.innerHTML = '';

    muscles.forEach(muscle => {
      const card = document.createElement('div');
      card.innerHTML = '<h3>' + muscle.name + '</h3>' +
                       '<p>' + muscle.description + '</p>' +
                       '<p>Zone: ' + muscle.bodyZone + '</p>';
      list.appendChild(card);
    });

  });