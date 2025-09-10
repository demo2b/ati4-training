document.querySelectorAll('.push-image-edito-wrapper').forEach((pushImageEdito) => {

    const accordions = pushImageEdito.querySelectorAll('.accordion-wrapper');
    let activeAccordion = pushImageEdito.querySelector('.accordion-wrapper.active');

    accordions.forEach((accordion) => {
        accordion.addEventListener('click', () => {
            if(!accordion.classList.contains('active')) {

                activeAccordion.classList.toggle('active');
                activeAccordion.querySelectorAll('span').forEach((span) => {
                    span.classList.toggle('hidden');
                })

                accordion.classList.toggle('active');
                accordion.querySelectorAll('span').forEach((span) => {
                    span.classList.toggle('hidden');
                })

                // Setting active accordion
                activeAccordion = accordion;
            }
        })
    })
})